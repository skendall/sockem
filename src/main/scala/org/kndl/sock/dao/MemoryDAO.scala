package org.kndl.sock.dao

import org.kndl.sock.model.{SockLink, Sock}
import java.util.concurrent.atomic.AtomicInteger
import st.sparse.persistentmap.PersistentMap
import scala.slick.session.Database

object MemoryDAO extends DAO {

  val id: AtomicInteger = new AtomicInteger(1)

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
    socks ++= Map(s.sockId -> s)
    socksByName ++= Map(s.name -> s)
    s
  }

  def link(aId: Int, bId: Int): Option[SockLink] = {
    val a = socks.get(aId)
    val b = socks.get(bId)
    if(a.isDefined && b.isDefined) {
      val link = new SockLink(id.getAndIncrement(),a.get,b.get)
      links ++= Seq(link)
      Option(link)
    } else {
      Option(null)
    }
  }

  def getLinks(id: Int): Seq[SockLink] = {
    links.filter( l => l.a.sockId == id || l.b.sockId == id)
  }
}
