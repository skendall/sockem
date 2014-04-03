package org.kndl.sock.aglo.spf

import org.kndl.sock.{E, V, G}

class Dijkstra(val graph: G) {
  def computeSPF(source: V, target: V) = {
    val edges = graph.edges
    var dist = Map(source -> 0).withDefaultValue(Int.MaxValue)
    var unvisiteds = edges.flatMap { case E(s, e, _) => Seq(s, e) }.toSet
    var shortestHops = Map[V, V]()

    def nodeEdges(n: V) = edges.filter(_.vA == n)
    def minU = unvisiteds.reduce((a, b) => if (dist(a) < dist(b)) a else b)

    while (unvisiteds.nonEmpty) {
      val u = minU
      unvisiteds -= u
      for (e <- nodeEdges(u); alt = dist(u) + e.w; if alt < dist(e.vB)) {
        dist += e.vB -> alt
        shortestHops += e.vB -> u
      }
    }

    def trace(u: V, path: Seq[V] = Seq()): Seq[V] =
      if (!shortestHops.contains(u)) u +: path
      else trace(shortestHops(u), u +: path)

    (dist(target), trace(target))
  }
}
