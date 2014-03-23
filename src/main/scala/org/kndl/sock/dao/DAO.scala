package org.kndl.sock.dao

import org.kndl.sock.model.{SockLink, Sock}

trait DAO {
  def getById(id: Int): Option[Sock]
  def getByName(name: String): Option[Sock]
  def get(): Map[Int,Sock]
  def create(name: String): Sock
  def link(aId: Int, bId: Int): Option[SockLink]
  def getLinks(id: Int): Seq[SockLink]
}