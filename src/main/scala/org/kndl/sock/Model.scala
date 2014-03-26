package org.kndl.sock


class V(val name: String) extends scala.Serializable

class E(val vA: V, val vB: V, val w: Double) extends scala.Serializable

class G(val name: String) extends scala.Serializable {

  var vertices: Seq[V] = Seq()

  var edges: Seq[E] = Seq()

  def ++(v: V) = vertices = vertices :+ v

  def +|(v1: V, v2: V, w: Double):E = {
    val e = new E(v1,v2,w)
    edges = edges :+ e
    e
  }

}