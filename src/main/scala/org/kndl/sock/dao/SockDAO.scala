package org.kndl.sock.dao

import org.kndl.sock.model.{SockLink, Sock}
import java.util.concurrent.atomic.AtomicInteger

trait DAO {
  def getById(id: Int): Option[Sock]
  def getByName(name: String): Option[Sock]
  def get(): Map[Int,Sock]
  def create(name: String): Sock
  def link(aId: Int, bId: Int): Option[SockLink]
  def getLinks(id: Int): Seq[SockLink]
}

object SockDAO extends DAO {

  val id: AtomicInteger = new AtomicInteger()

  var socks: Map[Int,Sock] = Map()
  var socksByName: Map[String,Sock] = Map()
  var links: Seq[SockLink] = Seq()

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
    socks ++= Map(s.id -> s)
    socksByName ++= Map(s.name -> s)
    s
  }

  def link(aId: Int, bId: Int): Option[SockLink] = {
    val a = socks.get(aId)
    val b = socks.get(bId)
    if(a.isDefined && b.isDefined) {
      val link = new SockLink(a.get,b.get)
      links ++= Seq(link)
      Option(link)
    } else {
      Option(null)
    }
  }

  def getLinks(id: Int): Seq[SockLink] = {
    links.filter( l => l.a.id == id || l.b.id == id)
  }
}
