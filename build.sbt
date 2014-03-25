name := "sock"

version := "0.1"

scalaVersion := "2.10.3"

resolvers += Resolver.sonatypeRepo("snapshots")

libraryDependencies ++= Seq(
    "net.fwbrasil" %% "zoot-core" % "1.0-RC1",
    "net.fwbrasil" %% "zoot-finagle" % "1.0-RC1",
    "st.sparse" %% "persistent-map" % "0.1-SNAPSHOT",
    "com.typesafe.slick" %% "slick" % "2.0.1",
    "com.typesafe.akka" %% "akka-actor" % "2.3.0"
)