name := "sock"

version := "0.1"

scalaVersion := "2.10.3"

resolvers ++= Seq("snapshots", "releases").map(Resolver.sonatypeRepo)

scalacOptions in Test ++= Seq("-Yrangepos")

libraryDependencies ++= Seq(
    "org.scalatest" %% "scalatest" % "2.1.0" % "test",
    "net.fwbrasil" %% "zoot-core" % "1.0-RC1",
    "net.fwbrasil" %% "zoot-finagle" % "1.0-RC1",
    "com.typesafe.akka" %% "akka-actor" % "2.3.0",
    "org.scala-lang" %% "scala-pickling" % "0.8.0-SNAPSHOT"
)

net.virtualvoid.sbt.graph.Plugin.graphSettings