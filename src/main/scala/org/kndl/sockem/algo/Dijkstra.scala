package org.kndl.sockem.algo

import org.kndl.sockem.{Edge, Vertex, Graph}

object Dijkstra {
  def apply(graph: Graph, source: Vertex, target: Vertex) = {
    val edges = graph.edges
    var dist = Map(source -> 0.0).withDefaultValue(Double.MaxValue)
    var unvisiteds = edges.flatMap { case Edge(s, e, _) => Seq(s, e) }.toSet
    var shortestHops = Map[Vertex, Vertex]()

    def nodeEdges(n: Vertex) = edges.filter(_.vA == n)
    def minU = unvisiteds.reduce((a, b) => if (dist(a) < dist(b)) a else b)

    while (unvisiteds.nonEmpty) {
      val u = minU
      unvisiteds -= u
      for (e <- nodeEdges(u); alt = dist(u) + e.w; if alt < dist(e.vB)) {
        dist += e.vB -> alt
        shortestHops += e.vB -> u
      }
    }

    def trace(u: Vertex, path: Seq[Vertex] = Seq()): Seq[Vertex] =
      if (!shortestHops.contains(u)) u +: path
      else trace(shortestHops(u), u +: path)

    (dist(target), trace(target))
  }
}
