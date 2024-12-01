ThisBuild / organization := "com.ifba"
ThisBuild / version := "1.0"

// the Scala version that will be used for cross-compiled libraries
ThisBuild / scalaVersion := "2.13.8"

// Workaround for scala-java8-compat issue affecting Lagom dev-mode
// https://github.com/lagom/lagom/issues/3344
ThisBuild / libraryDependencySchemes +=
  "org.scala-lang.modules" %% "scala-java8-compat" % VersionScheme.Always

val macwire = "com.softwaremill.macwire" %% "macros" % "2.3.3" % "provided"
val scalaTest = "org.scalatest" %% "scalatest" % "3.1.1" % Test

lazy val `microservice---audit` = (project in file("."))
  .aggregate(`microservice---audit-api`, `microservice---audit-impl`, `microservice---audit-stream-api`, `microservice---audit-stream-impl`)

lazy val `microservice---audit-api` = (project in file("microservice---audit-api"))
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslApi
    )
  )

lazy val `microservice---audit-impl` = (project in file("microservice---audit-impl"))
  .enablePlugins(LagomScala)
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslPersistenceCassandra,
      lagomScaladslKafkaBroker,
      lagomScaladslTestKit,
      macwire,
      scalaTest
    )
  )
  .settings(lagomForkedTestSettings)
  .dependsOn(`microservice---audit-api`)

lazy val `microservice---audit-stream-api` = (project in file("microservice---audit-stream-api"))
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslApi
    )
  )

lazy val `microservice---audit-stream-impl` = (project in file("microservice---audit-stream-impl"))
  .enablePlugins(LagomScala)
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslTestKit,
      macwire,
      scalaTest
    )
  )
  .dependsOn(`microservice---audit-stream-api`, `microservice---audit-api`)
