package org.kndl.sock

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global


class SockService extends SockAPI {

  def create(name: String): Future[V] = ???

  def getById(id: Long): Future[Option[V]] = ???

  def getByName(name: String): Future[Option[V]] = ???

  def get(): Future[Map[Long, V]] = ???

  def link(idA: Long, idB: Long): Future[Option[E]] = ???

  def getLinks(id: Long): Future[Seq[E]] = ???

  def createGraph(name: String): Future[G] = {
    val g = new G(1,"test",Seq())
    GraphStore.saveGraph(g)
    Future(g)
  }

  def addEdge(id: Long, eid: Long): Future[G] = ???

  def findShortestPath(gid: Long, vA: Long, vB: Long): Future[G] = ???

  def getGraph(name: String): Future[G] = {
    val g = GraphStore.graph(name)
    if(g.isDefined)
      Future(g.get)
    else
      Future(null)
  }
}
