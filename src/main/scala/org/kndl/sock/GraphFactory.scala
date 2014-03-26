package org.kndl.sock

import java.util.concurrent.atomic.{AtomicLong, AtomicInteger}
import scala.slick.driver.H2Driver.simple._
import scala.slick.direct.{SlickBackend, AnnotationMapper}
import scala.slick.driver.H2Driver
import org.kndl.sock.{E, V, G}

object GraphFactory {

  val id: AtomicLong = new AtomicLong(1)

  val backend = new SlickBackend(H2Driver, AnnotationMapper)

  def createGraph(name: String, edges: Seq[E]):G = {
    new G(id.getAndIncrement,name,edges)
  }

  def createVertex(name: String):V = {
    new V(id.getAndIncrement,name)
  }

  def createEdge(vA: V, vB: V, w: Double):E = {
    new E(id.getAndIncrement,vA,vB,w)
  }

}