package com.example.auditservice.impl

import akka.NotUsed
import com.example.auditservice.api.AuditService
import com.example.auditservice.impl.AuditService._
import com.lightbend.lagom.scaladsl.api.ServiceCall
import com.lightbend.lagom.scaladsl.persistence.PersistentEntityRegistry

import scala.concurrent.ExecutionContext
import akka.cluster.sharding.typed.scaladsl.ClusterSharding
import akka.util.Timeout

import scala.concurrent.duration._

class AuditServiceImpl(
    clusterSharding: ClusterSharding,
    reportRepository: AuditReportRepository
)(implicit ec: ExecutionContext)
    extends AuditService {

  implicit val timeout = Timeout(5.seconds)

  private def entityRef(id: String) =
    clusterSharding.entityRefFor(AuditService.typeKey, id)

  override def startAudit(id: String, sector: String): ServiceCall[NotUsed, String] = ServiceCall { _ =>
    entityRef(id).ask(reply => StartAudit(id, sector, reply)).map {
      case Accepted(summary) => s"Audit ${summary.auditId} started"
      case Rejected(reason)  => throw new IllegalStateException(reason)
    }
  }

  override def addCriteria(id: String, criteria: String, score: Int): ServiceCall[NotUsed, String] = ServiceCall { _ =>
    entityRef(id).ask(reply => AddCriteria(id, criteria, score, reply)).map {
      case Accepted(summary) => s"Criteria added to audit ${summary.auditId}"
      case Rejected(reason)  => throw new IllegalStateException(reason)
    }
  }

  override def finalizeAudit(id: String): ServiceCall[NotUsed, String] = ServiceCall { _ =>
    entityRef(id).ask(reply => FinalizeAudit(id, reply)).map {
      case Accepted(summary) => s"Audit ${summary.auditId} finalized"
      case Rejected(reason)  => throw new IllegalStateException(reason)
    }
  }
}
