package org.kndl.sock

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import org.kndl.sock.model.Sock
import org.kndl.sock.dao.SockDAO

class SockService extends SockAPI {

  def getById(id: Int): Future[Option[Sock]] = Future.successful(SockDAO.getById(id))

  def getByName(name: String): Future[Option[Sock]] = Future.successful(SockDAO.getByName(name))

  def create(name: String): Future[Sock] = Future.successful(SockDAO.create(name))

  def get(): Future[Map[Int,Sock]] = Future.successful(SockDAO.get())

}
