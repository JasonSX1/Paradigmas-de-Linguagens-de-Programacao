import com.lightbend.lagom.scaladsl.server._
import com.softwaremill.macwire._

class HelloLoader extends LagomApplicationLoader {
  override def load(context: LagomApplicationContext): LagomApplication =
    new HelloApplication(context)

    override def loadDevMode(context: LagomApplicationContext): LagomApplication =
    new HelloApplication(context)

  override def describeService = Some(readDescriptor[HelloService])
}

abstract class HelloApplication(context: LagomApplicationContext)
  extends LagomApplication(context) {

  override lazy val lagomServer = serverFor[HelloService](wire[HelloServiceImpl])
}
