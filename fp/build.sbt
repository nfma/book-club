name := "Book Club"

version := "0.1"

scalaVersion := "2.11.2"

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "2.2.0" % "test" withSources() withJavadoc(),
  "org.scalacheck" %% "scalacheck" % "1.11.5" % "test" withSources() withJavadoc()
)

resolvers ++= Seq(
  "Sonatype Releases" at "http://oss.sonatype.org/content/repositories/releases"
)