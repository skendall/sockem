package org.kndl.sock.ws

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import org.kndl.sock._

class SockService extends SockAPI {

  def createGraph(name: String): Future[G] = {
    val g = GraphFactory.createGraph(name)
    FileGraphStore.saveGraph(g)
    Future(g)
  }

  def getGraph(name: String): Future[G] = {
    val g = FileGraphStore.graph(name)
    if(g.isDefined) {
      Future(g.get)
    } else {
      Future(null)
    }
  }

  def getAllGraphs(): Future[Seq[String]] = Future(FileGraphStore.graphs())

  def createVertex(gname: String, vname: String): Future[Option[V]] = {
    val g = FileGraphStore.graph(gname)
    if(g.isDefined) {
      val v = GraphFactory.createVertex(g.get,vname)
      FileGraphStore.saveGraph(g.get)
      Future(Option(v))
    } else {
      Future(Option(null))
    }
  }

  def getVertex(gname: String, vname: String): Future[Option[V]] = Future(FileGraphStore.vertex(gname,vname))

  def getVertices(gname: String): Future[Map[String, V]] = Future(FileGraphStore.)

  def getEdges(gname: String, vname: String): Future[Seq[E]] = ???

  def addEdge(gname: String, vname1: String, vname2: String, weight: Double): Future[G] = ???

  def findShortestPath(gid: String, vA: String, vB: String): Future[G] = ???

}
