package org.kndl.sockem

import org.kndl.sockem.algo.Dijkstra

/**
 * The base class for manipulating graph objects.
 *
 * @param name
 */

case class Graph(val name: String) extends scala.Serializable {

  /**
   * Vertices
   */

  private var vertexSet: Set[Vertex] = Set()

  /**
   * Edges
   */

  private var edgeSet: Set[Edge] = Set()

  /**
   * Retrieve a single vertex from this graph.
   *
   * @param name
   * @return
   */

  def vertex(name: String):Option[Vertex] = vertexSet.find { v => v.name == name }

  /**
   * All vertices for this graph.
   *
   * @return
   */

  def vertices:Set[Vertex] = vertexSet

  /**
   * All edges in this graph.
   *
   * @return
   */

  def edges:Set[Edge] = edgeSet

  /**
   * Retrieve an edge from this graph.
   *
   * @param vA
   * @param vB
   * @return
   */

  def edge(vA: Vertex, vB: Vertex):Option[Edge] = edgeSet.find { e => e.vA == vA && e.vB == vB }

  /**
   * Retrieve all edges that the specified vertex is connected to.
   *
   * @param v
   * @return
   */

  def edges(v: Vertex): Set[Edge] = edgeSet.filter { e => e.vA == v || e.vB == v }

  def isCyclic: Boolean = ???

  /**
   * Adds a vertex to this graph.
   *
   * @param v
   * @return
   */

  def ++(v: Vertex):Graph = {
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
    +|+ (Vertex(vA),Vertex(vB),wA,wB)
  }

  /**
   * Adds two directed edges from one vertex to another and vice versa to this graph.
   *
   * @param vA
   * @param vB
   * @param wA
   * @param wB
   */

  def +|+(vA: Vertex, vB: Vertex, wA: Double, wB: Double) {
    this +| (vA,vB,wA)
    this +| (vB,vA,wB)
  }

  /**
   * Adds a sequence of directed edges to this graph.
   *
   * @param edges
   * @return
   */

  def +|(edges:Seq[Edge]):Graph = {
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

  def +|(vA: String, vB: String, w: Double):Graph = {
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

  def +|(vA: Vertex, vB: Vertex, w: Double):Graph = {
    this +| (vA -> (vB,w))
    this
  }

  /**
   * Adds a directed edge to this graph.
   * @param e
   * @return
   */

  def +|(e: Edge):Graph = {
    if(!vertices.find{ vertex => vertex.name == e.vA || vertex.name == e.vB }.isDefined)
      null

    val edge = edgeSet.find{ edge => edge.vA == e.vA && edge.vB == e.vB }
    if(edge.isDefined) {
      edgeSet = edgeSet -- edge
      edgeSet = edgeSet + new Edge(e.vA,e.vB,e.w)
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

  def --(v: Vertex):Graph = {
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

  def -|(e: Edge):Graph = {
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

  def ~(v1: Vertex, v2: Vertex) = Dijkstra(this,v1,v2)

  /**
   * Provides functionality to visit the nodes in the shortest path between
   * v1 and v2 and act upon each node visited.
   *
   * @param v1
   * @param v2
   * @param f
   * @return
   */

  def traverseShortestPath(v1: Vertex, v2: Vertex)( f: Vertex => Unit): Seq[Vertex] = {
    var path = Seq[Vertex]()
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

  def visitAll(start: Vertex)(f: Vertex => Unit): Graph = {
    var visited: Map[Vertex, Boolean] = vertices.map { v => (v, false) }.toMap
    def visit(v: Vertex)(f: Vertex => Unit) {
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