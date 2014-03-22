name := "sock"

version := "0.1"

scalaVersion := "2.10.3"

resolvers += Resolver.sonatypeRepo("snapshots")

libraryDependencies ++= Seq(
    "net.fwbrasil" % "zoot-core_2.10" % "1.0-RC1",
    "net.fwbrasil" % "zoot-finagle_2.10" % "1.0-RC1",
    "st.sparse" %% "persistent-map" % "0.1-SNAPSHOT",
    "com.h2database" % "h2" % "1.3.175"
)