name := "Book Club"

version := "0.1"

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  "org.scalatest" % "scalatest_2.11" % "2.2.0" % "test" withSources() withJavadoc()
)
