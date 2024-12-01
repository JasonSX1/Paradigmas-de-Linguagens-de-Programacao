package com.ifba.microserviceauditstream.api

import akka.NotUsed
import akka.stream.scaladsl.Source
import com.lightbend.lagom.scaladsl.api.{Descriptor, Service, ServiceCall}

/**
  * The Microservice - Audit stream interface.
  *
  * This describes everything that Lagom needs to know about how to serve and
  * consume the MicroserviceAuditStream service.
  */
trait MicroserviceAuditStreamService extends Service {

  def stream: ServiceCall[Source[String, NotUsed], Source[String, NotUsed]]

  override final def descriptor: Descriptor = {
    import Service._

    named("microservice---audit-stream")
      .withCalls(
        namedCall("stream", stream)
      ).withAutoAcl(true)
  }
}

