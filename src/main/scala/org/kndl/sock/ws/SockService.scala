package org.kndl.sock.ws

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import org.kndl.sock._

class SockService extends SockAPI {

  def createGraph(name: String): Future[G] = {
    val g = GraphFactory.createGraph(name)
    GraphStore.saveGraph(g)
    Future(g)
  }

  def getGraph(name: String): Future[G] = {
    val g = GraphStore.graph(name)
    if(g.isDefined) {
      Future(g.get)
    } else {
      Future(null)
    }
  }

  def getAllGraphs(): Future[Seq[String]] = Future(GraphStore.graphs())

  def createVertex(gname: String, vname: String): Future[Option[V]] = {
    val g = GraphStore.graph(gname)
    if(g.isDefined) {
      val v = GraphFactory.createVertex(g.get,vname)
      GraphStore.saveGraph(g.get)
      Future(Option(v))
    } else {
      Future(Option(null))
    }
  }

  def getVertex(gname: String, vname: String): Future[Option[V]] = ???

  def getVertices(gname: String): Future[Map[Long, V]] = ???

  def getEdges(gname: String, vname: String): Future[Seq[E]] = ???

  def addEdge(gname: String, vname1: String, vname2: String, weight: Double): Future[G] = ???

  def findShortestPath(gid: String, vA: String, vB: String): Future[G] = ???

}
