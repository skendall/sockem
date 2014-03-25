package org.kndl.sock

import scala.concurrent.Future


class SockService extends SockAPI {

  def create(name: String): Future[V] = ???

  def getById(id: Long): Future[Option[V]] = ???

  def getByName(name: String): Future[Option[V]] = ???

  def get(): Future[Map[Long, V]] = ???

  def link(idA: Long, idB: Long): Future[Option[E]] = ???

  def getLinks(id: Long): Future[Seq[E]] = ???

  def createGraph(name: String): Future[G] = ???

  def addEdge(id: Long, eid: Long): Unit = ???

  def findShortestPath(gid: Long, vA: Long, vB: Long): Unit = ???
}
