name := "sockem"

version := "0.4"

organization := "org.kndl"

scalaVersion := "2.10.3"

publishTo := Some(Resolver.file("file",  new File( "maven2" )) )

libraryDependencies ++= Seq(
    "org.scalatest" %% "scalatest" % "2.1.0" % "test"
)
