package org.kndl.sock.slick.h2

import scala.slick.driver.H2Driver.simple._

class SockLink(tag: Tag) extends Table[(Int,Sock,Sock)](tag,"socklinks") {

  def sockLinkId = column[Int]("id")
  def sockA = column[Int]("sockAId")
  def sockB = column[Int]("sockBId")

  def * = (sockLinkId,sockA,sockB)
}
