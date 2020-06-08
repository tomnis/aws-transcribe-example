import sbt._

object Dependencies {
  lazy val awsTranscribeStreaming = "software.amazon.awssdk" % "transcribestreaming" % "2.13.31"
  lazy val javamp3 = "fr.delthas" % "javamp3" % "1.0.1"
  lazy val jlayer = "javazoom" % "jlayer" % "1.0.1"
  lazy val sampledSp = "com.tagtraum" % "pcmsampledsp" % "0.9.5"
  lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.1.1"
  lazy val slf4j = "org.slf4j" % "slf4j-simple" % "1.7.28"
  lazy val tinylog = "org.tinylog" % "tinylog" % "1.3.6"
  lazy val twitter4s = "com.danielasfregola" %% "twitter4s" % "6.2"
}
