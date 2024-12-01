package com.ifba.microserviceauditstream.impl

import com.lightbend.lagom.scaladsl.api.ServiceCall
import com.ifba.microserviceauditstream.api.MicroserviceAuditStreamService
import com.ifba.microserviceaudit.api.MicroserviceAuditService

import scala.concurrent.Future

/**
  * Implementation of the MicroserviceAuditStreamService.
  */
class MicroserviceAuditStreamServiceImpl(microserviceAuditService: MicroserviceAuditService) extends MicroserviceAuditStreamService {
  def stream = ServiceCall { hellos =>
    Future.successful(hellos.mapAsync(8)(microserviceAuditService.hello(_).invoke()))
  }
}
