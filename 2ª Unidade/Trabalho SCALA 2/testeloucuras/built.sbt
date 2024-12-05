name := "MeuProjetoLagom"

version := "1.0.0"

scalaVersion := "2.12.20"

// Configuração para o Lagom Framework
enablePlugins(LagomScala)

lazy val root = (project in file("."))
  .settings(
    // Dependências principais
    libraryDependencies ++= Seq(
      "com.lightbend.lagom" %% "lagom-scaladsl-server" % "1.6.7", // Lagom server
      "com.lightbend.lagom" %% "lagom-scaladsl-api" % "1.6.7",    // Lagom API
      "com.lightbend.lagom" %% "lagom-javadsl-persistence-cassandra" % "1.6.7", // Cassandra
      "org.scalatest" %% "scalatest" % "3.2.15" % Test           // Testes
    ),
    fork in run := true // Isola os processos ao rodar o projeto
  )
