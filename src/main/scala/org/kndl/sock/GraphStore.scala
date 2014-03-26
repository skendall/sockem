package org.kndl.sock

import java.io.{File, FileOutputStream}
import scala.pickling._
import scala.pickling.binary._
import binary._

object GraphStore {
  def saveGraph(graph: G) = {
    val str = new StringOutput()
    graph.pickleTo(str)
    println(str.result())
  }
}
