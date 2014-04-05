package org.kndl.sockem.ws

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import org.kndl.sockem._

class SockService extends SockAPI {

  def createGraph(name: String): Future[G] = {
    val g = new G(name)
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

  def getAllGraphs(): Future[Set[String]] = Future(FileGraphStore.graphs())

  def createVertex(gname: String, vname: String): Future[Option[V]] = {
    val g = FileGraphStore.graph(gname)
    if(g.isDefined) {
      val v = new V(vname)
      g.get ++ v
      FileGraphStore.saveGraph(g.get)
      Future(Option(v))
    } else {
      Future(Option(null))
    }
  }

  def getVertex(gname: String, vname: String): Future[Option[V]] = Future(FileGraphStore.vertex(gname,vname))

  def getVertices(gname: String): Future[Set[V]] = Future(FileGraphStore.vertices(gname))

  def getEdges(gname: String, vname: String): Future[Set[E]] = Future(FileGraphStore.edges(gname,FileGraphStore.vertex(gname,vname).get))

  def addEdge(gname: String, vname1: String, vname2: String, weight: Double): Future[E] = {
    val v1 = FileGraphStore.vertex(gname,vname1)
    val v2 = FileGraphStore.vertex(gname,vname2)
    if(v1.isDefined && v2.isDefined) {
      Future(FileGraphStore.saveEdge(gname,v1.get,v2.get,weight))
    } else {
      Future(null)
    }
  }

  def findShortestPath(gid: String, vA: String, vB: String): Future[G] = {
    val g = FileGraphStore.graph(gid)
    val spfGraph = G("spf")
    if(g.isDefined) {
      val graph = g.get
      val (d,p) = graph ~ (V(vA),V(vB))
      val i = p.grouped(2)
      while(i.hasNext) {
        val vs = i.next()
        spfGraph ++ vs(0) ++ vs(1) +| graph.edge(vs(0),vs(1)).get
      }
    }
    Future(spfGraph)
  }

}
