package org.kndl.sock

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class SockService extends SockAPI {

  def create(name: String): Future[V] = ???

  def getById(id: String): Future[Option[V]] = ???

  def getByName(name: String): Future[Option[V]] = ???

  def get(): Future[Map[Long, V]] = ???

  def link(idA: String, idB: String): Future[Option[E]] = ???

  def getLinks(id: String): Future[Seq[E]] = ???

  def createGraph(name: String): Future[G] = {
    val g = GraphFactory.createGraph(name,Seq())
    GraphStore.saveGraph(g)
    Future(g)
  }

  def addEdge(id: String, eid: String): Future[G] = ???

  def findShortestPath(gid: String, vA: String, vB: String): Future[G] = ???

  def getGraph(name: String): Future[G] = {
    val g = GraphStore.graph(name)
    if(g.isDefined)
      Future(g.get)
    else
      Future(null)
  }

  def getAllGraphs(): Future[Seq[String]] = {
    Future(GraphStore.graphs)
  }
}
