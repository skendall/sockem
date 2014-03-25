package org.kndl.sock.ds

import java.util.concurrent.atomic.AtomicInteger
import scala.slick.driver.H2Driver.simple._
import scala.slick.direct.{SlickBackend, AnnotationMapper}
import scala.slick.driver.H2Driver

object DataStore {

  val id: AtomicInteger = new AtomicInteger(1)

  val backend = new SlickBackend(H2Driver, AnnotationMapper)

  def createVertex(name: String) {
    Database.forURL("jdbc:h2:graph", driver = "org.h2.Driver") withSession { implicit session:Session =>

    }
  }

}