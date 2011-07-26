name := "Blueeyes Test API"

version := "0.1.0"

organization := "com.foo"

scalaVersion := "2.9.0-1"

resolvers ++= Seq(
  "Sonatype"    at "http://nexus.scala-tools.org/content/repositories/public",
  "Scala Tools" at "http://scala-tools.org/repo-snapshots/",
  "JBoss"       at "http://repository.jboss.org/nexus/content/groups/public/",
  "Akka"        at "http://akka.io/repository/"
)

seq(sbtassembly.Plugin.assemblySettings: _*)

libraryDependencies ++= Seq(
  "com.reportgrid" % "blueeyes_2.9.0-1" % "0.4.16" % "compile",
  "com.twitter" % "querulous" % "2.3.0" % "compile"
)
