package org.kndl.sock


final class V(val name: String) extends scala.Serializable {

  def ->(v: V,w: Double): E = {
    new E(this,v,w)
  }

  def <->(v: V, weightTo: Double, weightFrom: Double): Seq[E] = {
    Seq(new E(this,v,weightTo), new E(v,this,weightFrom))
  }

  override def equals(other: Any) = other.isInstanceOf[V] && other.asInstanceOf[V].name == name

}

final class E(val vA: V, val vB: V, val w: Double) extends scala.Serializable {

  override def equals(other: Any) =
      other.isInstanceOf[E] &&
      other.asInstanceOf[E].vA == vA &&
      other.asInstanceOf[E].vB == vB

}

final class G(val name: String) extends scala.Serializable {

  private var vertexList: Seq[V] = Seq()

  private var edgeList: Seq[E] = Seq()

  def vertex(name: String):Option[V] = {
    val v = vertexList.filter { v => v.name == name }.last
    Option(v)
  }

  def vertices:Seq[V] = vertexList

  def edge(vA: V, vB: V):Option[E] = edgeList.find { e => e.vA == vA && e.vB == vB }

  def edges(v: V): Seq[E] = edgeList.filter { e => e.vA == v || e.vB == v }

  def ++(v: V) = vertexList = vertexList :+ v

  def +|(vA: V, vB: V, w: Double):E = this +| (vA -> (vB,w))

  def +|(e: E):E = {
    if(!vertices.find{ vertex => vertex.name == e.vA || vertex.name == e.vB }.isDefined)
      null

    val edge = edgeList.find{ edge => edge.vA == e.vA && edge.vB == e.vB }
    if(edge.isDefined) {
      val idx = edgeList.indexOf(edge)
      println("idx = " + idx)
      edgeList = edgeList.updated(idx,new E(e.vA,e.vB,e.w))
    } else
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