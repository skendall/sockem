package org.kndl.sock.slick.h2

import scala.slick.driver.H2Driver.simple._

class Sock(tag: Tag) extends Table[(Int, String)](tag, "sock") {

  def sockId = column[Int]("id", O.PrimaryKey)
  def name = column[String]("name")

  def * = (sockId, name)
}