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

  private def readGraph(name: String): Option[G] = {
    val f = new File("data" + File.separator + name + ".g")
    if (f.exists) {
      var str = ""
      for (s <- Source.fromFile("data" + File.separator + name + ".g").getLines())
        str ++= s
      Option(str.unpickle[G])
    } else {
      Option(null)
    }
  }

  private def writeGraph(graph: G) {
    val pckl = graph.pickle
    val out = new PrintWriter(new File("data" + File.separator + graph.name+".g"))
    out.print(pckl.value)
    out.close()
  }
}
