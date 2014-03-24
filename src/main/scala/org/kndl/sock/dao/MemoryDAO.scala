package org.kndl.sock.dao

import org.kndl.sock.model.{SockLink, Sock}
import java.util.concurrent.atomic.AtomicInteger
import akka.actor.Actor

object MemoryDAO extends Actor with DAO {

  def getById(id: Int): Option[Sock] = ???

  def getByName(name: String): Option[Sock] = ???

  def get(): Map[Int, Sock] = ???

  def create(name: String): Sock = ???

  def link(aId: Int, bId: Int): Option[SockLink] = ???

  def getLinks(id: Int): Seq[SockLink] = ???

  def receive: Actor.Receive = ???

}
