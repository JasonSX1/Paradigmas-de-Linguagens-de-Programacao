// class AuditServiceLoader extends LagomApplicationLoader {
//   override def load(context: LagomApplicationContext): LagomApplication =
//     new AuditServiceApplication(context) with JdbcPersistenceComponents

//   override def describeService = Some(readDescriptor[AuditService])
// }

package com.example.auditservice.impl

import com.example.auditservice.api.AuditService
import com.lightbend.lagom.scaladsl.akka.discovery.AkkaDiscoveryComponents
import com.lightbend.lagom.scaladsl.broker.kafka.LagomKafkaComponents
import com.lightbend.lagom.scaladsl.devmode.LagomDevModeComponents
import com.lightbend.lagom.scaladsl.persistence.slick.SlickPersistenceComponents
import com.lightbend.lagom.scaladsl.server._
import com.softwaremill.macwire._
import play.api.db.HikariCPComponents
import play.api.libs.ws.ahc.AhcWSComponents
import akka.cluster.sharding.typed.scaladsl.Entity
import com.lightbend.lagom.scaladsl.playjson.JsonSerializerRegistry

import scala.concurrent.ExecutionContext

class AuditServiceLoader extends LagomApplicationLoader {

  override def load(context: LagomApplicationContext): LagomApplication =
    new AuditServiceApplication(context) with AkkaDiscoveryComponents

  override def loadDevMode(context: LagomApplicationContext): LagomApplication =
    new AuditServiceApplication(context) with LagomDevModeComponents

  override def describeService = Some(readDescriptor[AuditService])
}

trait AuditServiceComponents
    extends LagomServerComponents
    with SlickPersistenceComponents
    with HikariCPComponents
    with AhcWSComponents {

  implicit def executionContext: ExecutionContext

  // Bind the service that this server provides
  override lazy val lagomServer: LagomServer =
    serverFor[AuditService](wire[AuditServiceImpl])

  // Register the JSON serializer registry
  override lazy val jsonSerializerRegistry: JsonSerializerRegistry =
    AuditSerializerRegistry

  lazy val reportRepository: AuditReportRepository =
    wire[AuditReportRepository]
  readSide.register(wire[AuditReportProcessor])

  // Initialize the sharding for the Audit aggregate
  clusterSharding.init(
    Entity(AuditService.typeKey) { entityContext =>
      AuditService(entityContext)
    }
  )
}

abstract class AuditServiceApplication(context: LagomApplicationContext)
    extends LagomApplication(context)
    with AuditServiceComponents
    with LagomKafkaComponents
