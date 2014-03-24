name := "sock"

version := "0.1"

scalaVersion := "2.10.3"

resolvers += Resolver.sonatypeRepo("snapshots")

libraryDependencies ++= Seq(
    "net.fwbrasil" % "zoot-core_2.10" % "1.0-RC1",
    "net.fwbrasil" % "zoot-finagle_2.10" % "1.0-RC1",
    "com.h2database" % "h2" % "1.3.175",
    "com.typesafe.slick" % "slick_2.10" % "2.0.1",
    "com.typesafe.akka" % "akka-actor_2.10" % "2.3.0"
)