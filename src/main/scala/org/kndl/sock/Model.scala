package org.kndl.sock


class V(val name: String) extends scala.Serializable

class E(val vA: V, val vB: V, val w: Double) extends scala.Serializable

class G(val name: String) extends scala.Serializable {

  private var vertexList: Seq[V] = Seq()

  private var edgeList: Seq[E] = Seq()

  def vertex(name: String):Option[V] = {
    val v = vertexList.filter { v => v.name == name }.last
    Option(v)
  }

  def vertices:Seq[V] = vertexList

  def edge(vA: V, vB: V):Option[E] = {
    val e = edgeList.filter { e => e.vA == vA && e.vB == vB }.last
    Option(e)
  }

  def edges(v: V): Seq[E] = {
    edgeList.filter { e => e.vA == v || e.vB == v }
  }

  def ++(v: V) = vertexList = vertexList :+ v

  def +|(e: E):E = {
    edgeList = edgeList :+ e
    e
  }

  def --(v: V): Unit = {
    vertexList = vertexList.filter { vertex => vertex.name == v.name }
  }

  def -|(e: E): Unit = {
    edgeList = edgeList.filter { edge => edge.vA == e.vA && edge.vB == e.vB }
  }

}

sealed trait MetaData {
  implicit def lastUpdated: Long = System.currentTimeMillis()
}

case class VertexMetaData(data:Map[String,String]) extends MetaData
case class EdgeMetaData(data:Map[String,String]) extends MetaData
case class GraphMetaData(data:Map[String,String]) extends MetaData