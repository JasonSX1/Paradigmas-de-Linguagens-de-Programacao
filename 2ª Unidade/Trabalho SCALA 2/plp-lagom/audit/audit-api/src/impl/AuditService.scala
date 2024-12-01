package com.example.auditservice.impl

import java.time.Instant

import akka.actor.typed.ActorRef
import akka.actor.typed.Behavior
import akka.cluster.sharding.typed.scaladsl._
import akka.persistence.typed.PersistenceId
import akka.persistence.typed.scaladsl.RetentionCriteria
import akka.persistence.typed.scaladsl.Effect
import akka.persistence.typed.scaladsl.EventSourcedBehavior
import akka.persistence.typed.scaladsl.ReplyEffect
import com.lightbend.lagom.scaladsl.persistence.AggregateEvent
import com.lightbend.lagom.scaladsl.persistence.AggregateEventShards
import com.lightbend.lagom.scaladsl.persistence.AggregateEventTag
import com.lightbend.lagom.scaladsl.playjson.JsonSerializer
import com.lightbend.lagom.scaladsl.playjson.JsonSerializerRegistry
import play.api.libs.json._

import scala.collection.immutable.Seq

object AuditService {

  // Commands
  trait CommandSerializable
  sealed trait Command extends CommandSerializable
  final case class StartAudit(auditId: String, sector: String, replyTo: ActorRef[Confirmation]) extends Command
  final case class AddCriteria(auditId: String, criteria: String, score: Int, replyTo: ActorRef[Confirmation]) extends Command
  final case class FinalizeAudit(auditId: String, replyTo: ActorRef[Confirmation]) extends Command
  final case class GetAudit(auditId: String, replyTo: ActorRef[Summary]) extends Command

  // Replies
  final case class Summary(auditId: String, sector: String, criteria: Map[String, Int], finalized: Boolean)
  sealed trait Confirmation
  final case class Accepted(summary: Summary) extends Confirmation
  final case class Rejected(reason: String) extends Confirmation

  // Events
  sealed trait Event extends AggregateEvent[Event] {
    override def aggregateTag: AggregateEventTag[Event] = Event.Tag
  }

  object Event {
    val Tag: AggregateEventShards[Event] = AggregateEventTag.sharded[Event](numShards = 4)
  }

  final case class AuditStarted(auditId: String, sector: String) extends Event
  final case class CriteriaAdded(auditId: String, criteria: String, score: Int) extends Event
  final case class AuditFinalized(auditId: String, finalizedAt: Instant) extends Event

  // State
  final case class AuditState(
      auditId: String,
      sector: String,
      criteria: Map[String, Int],
      finalized: Boolean = false
  ) {
    def addCriteria(newCriteria: String, score: Int): AuditState =
      copy(criteria = criteria + (newCriteria -> score))

    def finalizeAudit: AuditState = copy(finalized = true)
  }

  val empty: AuditState = AuditState("", "", Map.empty)

  val typeKey: EntityTypeKey[Command] = EntityTypeKey[Command]("AuditService")

  def apply(persistenceId: PersistenceId): EventSourcedBehavior[Command, Event, AuditState] =
    EventSourcedBehavior.withEnforcedReplies[Command, Event, AuditState](
      persistenceId = persistenceId,
      emptyState = empty,
      commandHandler = (state, cmd) => handleCommand(state, cmd),
      eventHandler = (state, evt) => handleEvent(state, evt)
    ).withRetention(RetentionCriteria.snapshotEvery(numberOfEvents = 50, keepNSnapshots = 2))

  private def handleCommand(state: AuditState, cmd: Command): ReplyEffect[Event, AuditState] = {
    cmd match {
      case StartAudit(auditId, sector, replyTo) =>
        Effect.persist(AuditStarted(auditId, sector)).thenReply(replyTo)(_ =>
          Accepted(Summary(auditId, sector, Map.empty, finalized = false))
        )

      case AddCriteria(_, criteria, score, replyTo) if state.finalized =>
        Effect.reply(replyTo)(Rejected("Cannot add criteria to a finalized audit"))

      case AddCriteria(_, criteria, score, replyTo) =>
        Effect.persist(CriteriaAdded(state.auditId, criteria, score)).thenReply(replyTo)(_ =>
          Accepted(Summary(state.auditId, state.sector, state.criteria + (criteria -> score), finalized = false))
        )

      case FinalizeAudit(_, replyTo) if state.finalized =>
        Effect.reply(replyTo)(Rejected("Audit is already finalized"))

      case FinalizeAudit(_, replyTo) =>
        Effect.persist(AuditFinalized(state.auditId, Instant.now())).thenReply(replyTo)(_ =>
          Accepted(Summary(state.auditId, state.sector, state.criteria, finalized = true))
        )

      case GetAudit(_, replyTo) =>
        Effect.reply(replyTo)(Summary(state.auditId, state.sector, state.criteria, state.finalized))
    }
  }

  private def handleEvent(state: AuditState, evt: Event): AuditState = {
    evt match {
      case AuditStarted(auditId, sector) => state.copy(auditId = auditId, sector = sector)
      case CriteriaAdded(_, criteria, score) => state.addCriteria(criteria, score)
      case AuditFinalized(_, _) => state.finalizeAudit
    }
  }
}
