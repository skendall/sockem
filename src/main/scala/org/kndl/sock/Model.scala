package org.kndl.sock


class V(val name: String) extends scala.Serializable {

  def ->(v: V,w: Double): E = {
    new E(this,v,w)
  }

  def <->(v: V, weightTo: Double, weightFrom: Double): Seq[E] = {
    Seq(new E(this,v,weightTo), new E(v,this,weightFrom))
  }

  override def equals(other: Any) = other.isInstanceOf[V] && other.asInstanceOf[V].name == name

  override def toString = name

}

class E(val vA: V, val vB: V, val w: Double) extends scala.Serializable {

  override def equals(other: Any) =
      other.isInstanceOf[E] &&
      other.asInstanceOf[E].vA == vA &&
      other.asInstanceOf[E].vB == vB

  override def toString = vA + " -> " + vB + " (" + w + ")"

}

class G(val name: String) extends scala.Serializable {

  private var vertexList: Seq[V] = Seq()

  private var edgeList: Seq[E] = Seq()

  def vertex(name: String):Option[V] = {
    val v = vertexList.filter { v => v.name == name }.last
    Option(v)
  }

  def vertices:Seq[V] = vertexList

  def edge(vA: V, vB: V):Option[E] = edgeList.find { e => e.vA == vA && e.vB == vB }

  def edges(v: V): Seq[E] = edgeList.filter { e => e.vA == v || e.vB == v }

  def edges:Seq[E] = edgeList

  def ++(v: V):G = {
    vertexList = vertexList :+ v
    this
  }

  def +|(edges:Seq[E]):G = {
    for(e <- edges) this +| e
    this
  }

  def +|(vA: String, vB: String, w: Double):G = {
    val va = vertexList.find { vertex => vertex.name == vA }
    val vb = vertexList.find { vertex => vertex.name == vB }
    if(va.isDefined && vb.isDefined)
      this +| (va.get,vb.get,w)
    this
  }

  def +|(vA: V, vB: V, w: Double):G = {
    this +| (vA -> (vB,w))
    this
  }

  def +|(e: E):G = {
    if(!vertices.find{ vertex => vertex.name == e.vA || vertex.name == e.vB }.isDefined)
      null

    val edge = edgeList.find{ edge => edge.vA == e.vA && edge.vB == e.vB }
    if(edge.isDefined) {
      val idx = edgeList.indexOf(edge)
      edgeList = edgeList.updated(idx,new E(e.vA,e.vB,e.w))
    } else
      edgeList = edgeList :+ e
    this
  }

  def --(v: V):G = {
    vertexList = vertexList.filter { vertex => vertex.name == v.name }
    this
  }

  def -|(e: E):G = {
    edgeList = edgeList.filter { edge => edge.vA == e.vA && edge.vB == e.vB }
    this
  }

  override def toString = name + " - Vertices " + vertexList + " - Edges " + edgeList

}

sealed trait MetaData {
  implicit def lastUpdated: Long = System.currentTimeMillis()
}

case class VertexMetaData(data:Map[String,String]) extends MetaData
case class EdgeMetaData(data:Map[String,String]) extends MetaData
case class GraphMetaData(data:Map[String,String]) extends MetaData