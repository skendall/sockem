package org.kndl.sock

import java.io._
import scala.pickling._
import json._
import scala.io.Source

object GraphStore {

  val dataDir = new File("data")
  if(!dataDir.exists()) dataDir.mkdir()

  def saveGraph(graph: G):G = {
    writeGraph(graph)
    graph
  }

  def graph(name: String):Option[G] = {
    readGraph(name)
  }

  def graphs():Seq[String] = {
    dataDir.listFiles
      .filter(_.getName.endsWith(".g"))
      .map(f => f.getName.substring(0,f.getName.length-2))
  }

  def saveVertex(vertex: V):V = {
    writeVertex(vertex)
    vertex
  }

  def vertex(gname: String, vname: String):Option[V] = {
    val g = readGraph(gname)
    if(g.isDefined) {
      g.vert
    }
  }

  //TODO: refactor this to use a single generic read/write object

  private def readVertex(name: String): Option[V] = {
    val fName = "data" + File.separator + name + ".v"
    val f = new File(fName)
    if (f.exists) {
      var str = ""
      for (s <- Source.fromFile(fName).getLines())
        str ++= s
      Option(str.unpickle[V])
    } else {
      Option(null)
    }
  }

  private def writeVertex(vertex: V) {
    val pckl = vertex.pickle
    val out = new PrintWriter(new File("data" + File.separator + vertex.name + ".v"))
    out.print(pckl.value)
    out.close()
  }

  private def readGraph(name: String): Option[G] = {
    val fName = "data" + File.separator + name + ".g"
    val f = new File(fName)
    if (f.exists) {
      var str = ""
      for (s <- Source.fromFile(fName).getLines())
        str ++= s
      Option(str.unpickle[G])
    } else {
      Option(null)
    }
  }

  private def writeGraph(graph: G) {
    val pckl = graph.pickle
    val out = new PrintWriter(new File("data" + File.separator + graph.name + ".g"))
    out.print(pckl.value)
    out.close()
  }
}
