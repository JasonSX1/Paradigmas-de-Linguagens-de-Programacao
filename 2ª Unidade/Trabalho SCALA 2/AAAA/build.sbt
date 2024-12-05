import com.lightbend.lagom.core.LagomVersion.{ current => lagomVersion }

organization in ThisBuild := "com.example"

scalaVersion in ThisBuild := "2.13.5"

// Dependências utilizadas pelo projeto
val postgresDriver             = "org.postgresql"                % "postgresql"                                    % "42.2.18"
val macwire                    = "com.softwaremill.macwire"     %% "macros"                                        % "2.3.7" % "provided"
val scalaTest                  = "org.scalatest"                %% "scalatest"                                     % "3.2.2" % Test
val akkaDiscoveryKubernetesApi = "com.lightbend.akka.discovery" %% "akka-discovery-kubernetes-api"                 % "1.0.10"
val lagomScaladslAkkaDiscovery = "com.lightbend.lagom"          %% "lagom-scaladsl-akka-discovery-service-locator" % lagomVersion

lagomCassandraEnabled in ThisBuild := false
lagomKafkaEnabled in ThisBuild := false


ThisBuild / scalacOptions ++= List("-encoding", "utf8", "-deprecation", "-feature", "-unchecked", "-Xfatal-warnings")

// Configuração do Docker
def dockerSettings = Seq(
  dockerUpdateLatest := true,
  dockerBaseImage := getDockerBaseImage(),
  dockerUsername := sys.props.get("docker.username"),
  dockerRepository := sys.props.get("docker.registry")
)

def getDockerBaseImage(): String = sys.props.get("java.version") match {
  case Some(v) if v.startsWith("11") => "adoptopenjdk/openjdk11"
  case _                             => "adoptopenjdk/openjdk8"
}

// Configuração principal do projeto
lazy val root = (project in file("."))
  .enablePlugins(LagomScala)
  .settings(
    name := "LoaderServiceProject",
    version := "1.0.0",
    libraryDependencies ++= Seq(
      lagomScaladslApi,              // Lagom API
      lagomScaladslPersistenceJdbc,  // Persistência com JDBC
      lagomScaladslKafkaBroker,      // Kafka Broker
      macwire,                       // Injeção de dependências
      postgresDriver,                // Driver para PostgreSQL
      scalaTest                      // Framework para testes
    ),
    fork in run := true // Isola os processos ao rodar o projeto
  )
