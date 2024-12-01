import akka.NotUsed
import com.lightbend.lagom.scaladsl.api.Service
import com.lightbend.lagom.scaladsl.api.ServiceCall
//import com.lightbend.lagom.akka.management.scaladsl.api.ServiceCall

trait HelloService extends Service {
  def sayHello(name: String): ServiceCall[NotUsed, String]

  override final def descriptor = {
    import Service._
    named("hello")
      .withCalls(
        pathCall("/api/hello/:name", sayHello _)
      )
      .withAutoAcl(true)
  }
}
