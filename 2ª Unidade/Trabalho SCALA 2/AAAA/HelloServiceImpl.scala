import com.lightbend.lagom.scaladsl.api.ServiceCall
import akka.NotUsed

import scala.concurrent.Future

class HelloServiceImpl extends HelloService {
  override def sayHello(name: String): ServiceCall[NotUsed, String] = ServiceCall { _ =>
    Future.successful(s"Hello, $name!")
  }
}
