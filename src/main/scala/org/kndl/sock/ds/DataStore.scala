package org.kndl.sock.ds

import akka.actor.Actor
import org.kndl.sock.model.{SockLink, Sock}
import java.util.concurrent.atomic.AtomicInteger

case class Create(name: String)
case class Update(sock: Sock)
case class Delete(sock: Sock)
case class Get()
case class GetById(id: Int)
case class GetByName(name: String)
case class Link(aId: Int, bId: Int)

object DataStore extends Actor {

  val id: AtomicInteger = new AtomicInteger(1)

  var socks: Map[Int,Sock] = Map()
  var socksByName: Map[String,Sock] = Map()
  var links: Seq[SockLink] = Seq()

  def receive: Actor.Receive = {
    case Create(name) => {
      val sock = new Sock(id.getAndIncrement,name)
      socks ++ Map(sock.sockId,sock)
      sender ! sock
    }
    case Get => {
      sender ! socks
    }
    case Link(aId,bId) => {
      val a = socks.get(aId)
      val b = socks.get(bId)
      if(a.isDefined && b.isDefined) {
        val link = new SockLink(id.getAndIncrement(),a.get,b.get)
        links ++= Seq(link)
        sender ! Option(link)
      } else {
        sender ! Option(null)
      }
    }
  }

}