name := "Book Club"

version := "0.1"

scalaVersion := "2.11.0"

libraryDependencies ++= Seq(
  "org.scalatest" % "scalatest_2.11" % "2.2.0" % "test" withSources() withJavadoc()
)
