package org.kndl.sock.dao

import org.kndl.sock.model.Sock
import java.util.concurrent.atomic.AtomicInteger

trait DAO {
  def getById(id: Int): Option[Sock]
  def getByName(name: String): Option[Sock]
  def get(): Map[Int,Sock]
  def create(name: String): Sock
}

object SockDAO extends DAO {

  val id: AtomicInteger = new AtomicInteger()

  val socks: Map[Int,Sock] = Map()
  val socksByName: Map[String,Sock] = Map()

  def getById(id: Int): Option[Sock] = {
    socks.get(id)
  }

  def getByName(name: String): Option[Sock] = {
    socksByName.get(name)
  }

  def get(): Map[Int,Sock] = {
    socks
  }

  def create(name: String): Sock = {
    val s = new Sock(id.getAndIncrement(),name)
    socks ++ Map(s.id -> s)
    socksByName ++ Map(s.name -> s)
    s
  }

}
