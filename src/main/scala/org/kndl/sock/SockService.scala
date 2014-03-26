package org.kndl.sock

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import java.util.UUID


class SockService extends SockAPI {

  def create(name: String): Future[V] = ???

  def getById(id: UUID): Future[Option[V]] = ???

  def getByName(name: String): Future[Option[V]] = ???

  def get(): Future[Map[Long, V]] = ???

  def link(idA: UUID, idB: UUID): Future[Option[E]] = ???

  def getLinks(id: UUID): Future[Seq[E]] = ???

  def createGraph(name: String): Future[G] = {
    val g = GraphFactory.createGraph(name,Seq())
    GraphStore.saveGraph(g)
    Future(g)
  }

  def addEdge(id: UUID, eid: UUID): Future[G] = ???

  def findShortestPath(gid: UUID, vA: UUID, vB: UUID): Future[G] = ???

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
