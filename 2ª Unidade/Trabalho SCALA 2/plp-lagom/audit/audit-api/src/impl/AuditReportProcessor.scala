package com.example.auditservice.impl

import com.lightbend.lagom.scaladsl.persistence.AggregateEventTag
import com.lightbend.lagom.scaladsl.persistence.ReadSideProcessor
import com.lightbend.lagom.scaladsl.persistence.slick.SlickReadSide
import slick.dbio.DBIOAction
import akka.Done
import com.example.auditservice.impl.AuditService._

class AuditReportProcessor(readSide: SlickReadSide, repository: AuditReportRepository)
    extends ReadSideProcessor[Event] {

  override def buildHandler(): ReadSideProcessor.ReadSideHandler[Event] =
    readSide
      .builder[Event]("audit-report")
      .setGlobalPrepare(repository.createTable())
      .setEventHandler[AuditStarted] { envelope =>
        repository.createAudit(envelope.entityId, envelope.event.sector)
      }
      .setEventHandler[CriteriaAdded] { envelope =>
        repository.addCriteria(envelope.entityId, envelope.event.criteria, envelope.event.score)
      }
      .setEventHandler[AuditFinalized] { envelope =>
        repository.finalizeAudit(envelope.entityId, envelope.event.finalizedAt)
      }
      .build()

  override def aggregateTags: Set[AggregateEventTag[Event]] = Event.Tag.allTags
}
