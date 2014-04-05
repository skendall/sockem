name := "sockem"

version := "0.3"

organization := "org.kndl"

scalaVersion := "2.10.3"

publishTo := Some(Resolver.file("file",  new File( "maven" )) )

resolvers ++= Seq("snapshots", "releases").map(Resolver.sonatypeRepo)

libraryDependencies ++= Seq(
    "org.scalatest" %% "scalatest" % "2.1.0" % "test",
    "net.fwbrasil" %% "zoot-core" % "1.0-RC1",
    "net.fwbrasil" %% "zoot-finagle" % "1.0-RC1",
    "com.typesafe.akka" %% "akka-actor" % "2.3.0",
    "org.scala-lang" %% "scala-pickling" % "0.8.0-SNAPSHOT"
)
