import Dependencies._

ThisBuild / scalaVersion     := "2.13.2"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "org.wiggum"
ThisBuild / organizationName := "wiggum"

lazy val root = (project in file("."))
  .settings(
    name := "wiggum",
    libraryDependencies ++= Seq(
      awsTranscribeStreaming,
      javamp3,
      jlayer,
      sampledSp,
      slf4j,
      tinylog,
      twitter4s,
      scalaTest % Test
    )
  )

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
