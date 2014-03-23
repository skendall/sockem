package org.kndl.sock

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import org.kndl.sock.model.{SockLink, Sock}
import org.kndl.sock.dao.MemoryDAO

class SockService extends SockAPI {

  def getById(id: Int): Future[Option[Sock]] = Future.successful(MemoryDAO.getById(id))

  def getByName(name: String): Future[Option[Sock]] = Future.successful(MemoryDAO.getByName(name))

  def create(name: String): Future[Sock] = Future.successful(MemoryDAO.create(name))

  def get(): Future[Map[Int,Sock]] = Future.successful(MemoryDAO.get())

  def link(idA: Int, idB: Int): Future[Option[SockLink]] = Future.successful(MemoryDAO.link(idA,idB))

  def getLinks(id: Int): Future[Seq[SockLink]] = Future.successful(MemoryDAO.getLinks(id))
}
