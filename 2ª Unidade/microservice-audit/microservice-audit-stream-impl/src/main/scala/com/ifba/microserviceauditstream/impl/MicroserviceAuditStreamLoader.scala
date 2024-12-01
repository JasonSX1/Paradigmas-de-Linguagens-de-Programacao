package com.ifba.microserviceauditstream.impl

import com.lightbend.lagom.scaladsl.api.ServiceLocator.NoServiceLocator
import com.lightbend.lagom.scaladsl.server._
import com.lightbend.lagom.scaladsl.devmode.LagomDevModeComponents
import play.api.libs.ws.ahc.AhcWSComponents
import com.ifba.microserviceauditstream.api.MicroserviceAuditStreamService
import com.ifba.microserviceaudit.api.MicroserviceAuditService
import com.softwaremill.macwire._

class MicroserviceAuditStreamLoader extends LagomApplicationLoader {

  override def load(context: LagomApplicationContext): LagomApplication =
    new MicroserviceAuditStreamApplication(context) {
      override def serviceLocator: NoServiceLocator.type = NoServiceLocator
    }

  override def loadDevMode(context: LagomApplicationContext): LagomApplication =
    new MicroserviceAuditStreamApplication(context) with LagomDevModeComponents

  override def describeService = Some(readDescriptor[MicroserviceAuditStreamService])
}

abstract class MicroserviceAuditStreamApplication(context: LagomApplicationContext)
  extends LagomApplication(context)
    with AhcWSComponents {

  // Bind the service that this server provides
  override lazy val lagomServer: LagomServer = serverFor[MicroserviceAuditStreamService](wire[MicroserviceAuditStreamServiceImpl])

  // Bind the MicroserviceAuditService client
  lazy val microserviceAuditService: MicroserviceAuditService = serviceClient.implement[MicroserviceAuditService]
}
