import com.lightbend.lagom.core.LagomVersion.{ current => lagomVersion }

scalaVersion := "2.12.20"

val lagomScaladslAkkaDiscovery = "com.lightbend.lagom"          %% "lagom-scaladsl-akka-discovery-service-locator" % lagomVersion

// Nome do projeto
name := "meu-projeto-lagom"

// Versão do projeto
version := "1.0.0"

scalacOptions ++= Seq(
  "-Xmacro-settings:enable"
)

libraryDependencies += "com.lightbend.lagom" %% "lagom-akka-management-core" % "1.6.7"
libraryDependencies += "com.lightbend.lagom" %% "lagom-akka-management-scaladsl" % "1.6.7"
libraryDependencies += "com.lightbend.lagom" %% "lagom-api" % "1.6.7"
libraryDependencies += "com.lightbend.lagom" %% "lagom-client" % "1.6.7"
libraryDependencies += "com.lightbend.lagom" %% "lagom-server" % "1.6.7"
libraryDependencies += "com.lightbend.lagom" %% "lagom-scaladsl-api" % "1.6.7"
libraryDependencies += "com.lightbend.akka.management" %% "akka-management" % "1.4.1"
libraryDependencies += "com.softwaremill.macwire" %% "macros" % "2.5.8" % Provided
libraryDependencies += "com.softwaremill.macwire" %% "util" % "2.5.8"
libraryDependencySchemes += "org.scala-lang.modules" %% "scala-xml" % "2.3.0"

evictionErrorLevel := Level.Warn
