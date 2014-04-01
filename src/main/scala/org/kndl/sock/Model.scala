package org.kndl.sock


class V(val name: String) extends scala.Serializable

class E(val vA: V, val vB: V, val w: Double) extends scala.Serializable

class G(val name: String) extends scala.Serializable {

  private var vertices: Seq[V] = Seq()

  private var edges: Seq[E] = Seq()

  def vertex(name: String):Option[V] = {
    val v = vertices.filter { v => v.name == name }.last
    Option(v)
  }

  def edge(vA: V, vB: V):Option[E] = {
    val e = edges.filter { e => e.vA == vA && e.vB == vB }.last
    Option(e)
  }

  def edges(v: V): Seq[E] = {
    edges.filter { e => e.vA == v || e.vB == v }
  }

  def ++(v: V) = vertices = vertices :+ v

  def +|(e: E):E = {
    edges = edges :+ e
    e
  }

  def --(v: V): Unit = ???

  def -|(e: E): Unit = ???

}

sealed trait MetaData {
  implicit def lastUpdated: Long = System.currentTimeMillis()
}

case class VertexMetaData(data:Map[String,String]) extends MetaData
case class EdgeMetaData(data:Map[String,String]) extends MetaData
case class GraphMetaData(data:Map[String,String]) extends MetaData