package org.kndl.sock.algo

import org.kndl.sock.{V, G}

class DijkstraSPF {

  private class Node(vertex: V, visited: Boolean)

  def calculateSPF(graph: G, start: V, end: V): G = {
    var nodes: Map[V,Double] = Map()
    for( v <- graph.vertices) {
      if(v.name == start.name) {
        nodes ++= Map(v -> 0)
      } else {
        nodes ++= Map(v -> -1)
      }
      v.set
    }

    var current = start

    neighbors(graph,current)

    graph
  }

  def neighbors(graph: G, vertex: V): Seq[V] = graph.edges(vertex).filter { edge => edge.vA == vertex }.map { edge => edge.vB }
}
