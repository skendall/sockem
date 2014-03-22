package org.kndl.sock

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import org.kndl.sock.model.{SockLink, Sock}
import org.kndl.sock.dao.SockDAO

class SockService extends SockAPI {

  def getById(id: Int): Future[Option[Sock]] = Future.successful(SockDAO.getById(id))

  def getByName(name: String): Future[Option[Sock]] = Future.successful(SockDAO.getByName(name))

  def create(name: String): Future[Sock] = Future.successful(SockDAO.create(name))

  def get(): Future[Map[Int,Sock]] = Future.successful(SockDAO.get())

  def link(idA: Int, idB: Int): Future[Option[SockLink]] = Future.successful(SockDAO.link(idA,idB))

  def getLinks(id: Int): Future[Seq[SockLink]] = Future.successful(SockDAO.getLinks(id))
}
