package org.kndl.sock.aglo.spf

import org.kndl.sock.{V, G}

class Dijkstra(val graph: G) {

  var distance: Map[V,Double] = Map()
  var visited: Map[V,Boolean] = Map()

  def calculateSPF(start: V, end: V): G = {

    for( v <- graph.vertices) {
      if(v.name == start.name) {
        distance ++= Map(v -> 0.0)
      } else {
        distance ++= Map(v -> -1.0)
      }
      visited ++= Map(v -> false)
    }

    var current = start

    val neighbors = neighbors(graph,current)

    for(n <- neighbors) {

    }

    graph
  }

  def neighbors(graph: G, vertex: V): Seq[V] = graph.edges(vertex).filter { edge => edge.vA == vertex }.map { edge => edge.vB }
}
