package org.kndl.sockem

case class Edge(val vA: Vertex, val vB: Vertex, val w: Double) extends scala.Serializable {

  override def equals(other: Any) =
    other.isInstanceOf[Edge] &&
      other.asInstanceOf[Edge].vA == vA &&
      other.asInstanceOf[Edge].vB == vB

  override def toString = vA + " -> " + vB + " (" + w + ")"

}