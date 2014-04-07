package org.kndl.sockem

import org.kndl.sockem.algo.Dijkstra

/**
 * The base class for manipulating graph objects.
 *
 * @param name
 * @param undirected
 */

case class G(val name: String, undirected: Boolean = false) extends scala.Serializable {

  /**
   * Vertices
   */

  private var vertexSet: Set[V] = Set()

  /**
   * Edges
   */

  private var edgeSet: Set[E] = Set()

  /**
   * Retrieve a single vertex from this graph.
   *
   * @param name
   * @return
   */

  def vertex(name: String):Option[V] = {
    val v = vertexSet.filter { v => v.name == name }.last
    Option(v)
  }

  /**
   * All vertices for this graph.
   *
   * @return
   */

  def vertices:Set[V] = vertexSet

  /**
   * All edges in this graph.
   *
   * @return
   */

  def edges:Set[E] = edgeSet

  /**
   * Retrieve an edge from this graph.
   *
   * @param vA
   * @param vB
   * @return
   */

  def edge(vA: V, vB: V):Option[E] = edgeSet.find { e => e.vA == vA && e.vB == vB }

  /**
   * Retrieve all edges that the specified vertex is connected to.
   *
   * @param v
   * @return
   */

  def edges(v: V): Set[E] = edgeSet.filter { e => e.vA == v || e.vB == v }

  /**
   * Adds a vertex to this graph.
   *
   * @param v
   * @return
   */

  def ++(v: V):G = {
    vertexSet = vertexSet + v
    this
  }

  /**
   * Adds two directed edges from one vertex to another and vice versa to this graph. (convenience function)
   *
   * @param vA
   * @param vB
   * @param wA
   * @param wB
   */

  def +|+(vA: String, vB: String, wA: Double, wB: Double) {
    +|+ (V(vA),V(vB),wA,wB)
  }

  /**
   * Adds two directed edges from one vertex to another and vice versa to this graph.
   *
   * @param vA
   * @param vB
   * @param wA
   * @param wB
   */

  def +|+(vA: V, vB: V, wA: Double, wB: Double) {
    this +| (vA,vB,wA)
    this +| (vB,vA,wB)
  }

  /**
   * Adds a sequence of directed edges to this graph.
   *
   * @param edges
   * @return
   */

  def +|(edges:Seq[E]):G = {
    for(e <- edges) this +| e
    this
  }

  /**
   * Adds a directed edge from vertex vA to vertex vB with weight w to this graph. (convenience function)
   *
   * @param vA
   * @param vB
   * @param w
   * @return
   */

  def +|(vA: String, vB: String, w: Double):G = {
    val va = vertexSet.find { vertex => vertex.name == vA }
    val vb = vertexSet.find { vertex => vertex.name == vB }
    if(va.isDefined && vb.isDefined)
      this +| (va.get,vb.get,w)
    this
  }

  /**
   * Adds a directed edge from vertex vA to vertex vB with weight w to this graph.
   *
   * @param vA
   * @param vB
   * @param w
   * @return
   */

  def +|(vA: V, vB: V, w: Double):G = {
    this +| (vA -> (vB,w))
    this
  }

  /**
   * Adds a directed edge to this graph.
   * @param e
   * @return
   */

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

  /**
   * Removes a vertex and it's connected edges from this graph.
   *
   * @param v
   * @return
   */

  def --(v: V):G = {
    vertexSet = vertexSet.filter { vertex => vertex.name == v.name }
    edgeSet = edgeSet.filter { edge => edge.vA == v || edge.vB == v }
    this
  }

  /**
   * Removes an edge from this graph.
   *
   * @param e
   * @return
   */

  def -|(e: E):G = {
    edgeSet = edgeSet.filter { edge => edge.vA == e.vA && edge.vB == e.vB }
    this
  }

  /**
   * Calculates the shortest path from one vertex to another and returns a
   * tuple (distance[Double],path[Seq[V]]).
   *
   * @param v1
   * @param v2
   * @return
   */

  def ~(v1: V, v2: V) = Dijkstra(this,v1,v2)

  /**
   * Provides functionality to visit the nodes in the shortest path between
   * v1 and v2 and act upon each node visited.
   *
   * @param v1
   * @param v2
   * @param f
   * @return
   */

  def traverseShortestPath(v1: V, v2: V)( f: V => Unit): Seq[V] = {
    var path = Seq[V]()
    val (d,p) = Dijkstra(this,v1,v2)
    for(v <- p) {
      f(v)
      path = path :+ v
    }
    path
  }

  /**
   * Visits all nodes, starting from start, in the graph and acts upon them appropriately.
   *
   * @param start
   * @param f
   * @return
   */

  def visitAll(start: V)(f: V => Unit): G = {
    var visited: Map[V, Boolean] = vertices.map { v => (v, false) }.toMap
    def visit(v: V)(f: V => Unit) {
      visited += (v -> true)
      f(v)
      for (edge <- edges(v)) {
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

  /**
   * String override.
   *
   * @return
   */

  override def toString = name + " - Vertices " + vertexSet + " - Edges " + edgeSet

}