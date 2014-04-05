package org.kndl.sock

import org.kndl.sock.aglo.spf.Dijkstra

case class G(val name: String) extends scala.Serializable {

  private var vertexSet: Set[V] = Set()

  private var edgeSet: Set[E] = Set()

  def vertex(name: String):Option[V] = {
    val v = vertexSet.filter { v => v.name == name }.last
    Option(v)
  }

  def vertices:Set[V] = vertexSet

  def edge(vA: V, vB: V):Option[E] = edgeSet.find { e => e.vA == vA && e.vB == vB }

  def edges(v: V): Set[E] = edgeSet.filter { e => e.vA == v || e.vB == v }

  def edges:Set[E] = edgeSet

  def ++(v: V):G = {
    vertexSet = vertexSet + v
    this
  }

  def +|(edges:Seq[E]):G = {
    for(e <- edges) this +| e
    this
  }

  def +|(vA: String, vB: String, w: Double):G = {
    val va = vertexSet.find { vertex => vertex.name == vA }
    val vb = vertexSet.find { vertex => vertex.name == vB }
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

    val edge = edgeSet.find{ edge => edge.vA == e.vA && edge.vB == e.vB }
    if(edge.isDefined) {
      edgeSet = edgeSet -- edge
      edgeSet = edgeSet + new E(e.vA,e.vB,e.w)
    } else
      edgeSet = edgeSet + e
    this
  }

  def --(v: V):G = {
    vertexSet = vertexSet.filter { vertex => vertex.name == v.name }
    this
  }

  def -|(e: E):G = {
    edgeSet = edgeSet.filter { edge => edge.vA == e.vA && edge.vB == e.vB }
    this
  }

  def ~(v1: V, v2: V) = Dijkstra(this,v1,v2)

  def traverseShortestPath(v1: V, v2: V)( f: V => Unit): Seq[V] = {
    var path = Seq[V]()
    val (d,p) = Dijkstra(this,v1,v2)
    for(v <- p) {
      f(v)
      path = path :+ v
    }
    path
  }

  def visitAll(start: V)(f: V => Unit): G = {
    var visited: Map[V, Boolean] = vertices.map { v => (v, false) }.toMap
    def visit(v: V)(f: V => Unit) {
      visited += (start -> true)
      f(start)
      for (edge <- edges(start)) {
        if (!visited.get(edge.vB).get) {
          f(edge.vB)
          visited += (edge.vB -> true)
          visit(edge.vB)(f)
        }
      }
    }
    visit(start)(f)
    this
  }

  override def toString = name + " - Vertices " + vertexSet + " - Edges " + edgeSet

}