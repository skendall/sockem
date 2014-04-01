package org.kndl.sock

import java.io._
import scala.pickling._
import json._
import scala.io.Source

trait GraphStore {

  // graph persistence

  def saveGraph(graph: G):G
  def graph(name: String):Option[G]
  def graphs():Seq[String]

  // vertext persistence

  def saveVertex(gname:String, vertex:V):V
  def vertex(gname:String, vname:String):V

  // edge persistence

  def saveEdge(gname:String, vA:V, vB:V, w:Double):E
  def edges(gname:String, v:V):Seq[E]
  def edge(gname:String, vA:V, vB:V):Seq[E]

}

object FileGraphStore extends GraphStore {

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

  def saveVertex(gname: String, vertex: V):V = {
    val g = readGraph(gname)
    if(g.isDefined) {
      g.get ++ vertex
    }
    vertex
  }

  def vertex(gname: String, vname: String):Option[V] = {
    val g = readGraph(gname)
    if(g.isDefined && g.get.vertex(vname).isDefined) {
      Option(g.get.vertex(vname).get)
    } else {
      Option(null)
    }
  }

  def saveEdge(gname: String, edge: E): E = {
    val g = readGraph(gname)
    if(g.isDefined) {
      g.get +| edge
    }
    edge
  }

  def edges(gname: String, v: V): Seq[E] = {
    val g = readGraph(gname)
    if(g.isDefined) {
      g.get.
    }
  }

  def edge(gname: String, vA: V, vB: V): Seq[E] = ???

  // file helper functions

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
