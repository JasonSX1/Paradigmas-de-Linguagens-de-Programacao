package com.example.auditservice.impl

import java.time.Instant

import akka.Done
import slick.dbio.DBIO
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class AuditReportRepository(database: Database) {

  class AuditReportTable(tag: Tag) extends Table[(String, String, Boolean, Instant)](tag, "audit_report") {
    def auditId = column[String]("audit_id", O.PrimaryKey)
    def sector = column[String]("sector")
    def finalized = column[Boolean]("finalized")
    def finalizedAt = column[Option[Instant]]("finalized_at")

    def * = (auditId, sector, finalized, finalizedAt)
  }

  val reportTable = TableQuery[AuditReportTable]

  def createTable() = reportTable.schema.createIfNotExists

  def createAudit(auditId: String, sector: String): DBIO[Done] =
    reportTable += (auditId, sector, false, None)

  def addCriteria(auditId: String, criteria: String, score: Int): DBIO[Done] =
    DBIO.successful(Done) // Just a placeholder if you don't store criteria separately.

  def finalizeAudit(auditId: String, finalizedAt: Instant): DBIO[Done] =
    reportTable.filter(_.auditId === auditId).map(_.finalized).update(true)

  def findById(auditId: String): Future[Option[(String, String, Boolean, Instant)]] =
    database.run(reportTable.filter(_.auditId === auditId).result.headOption)
}
