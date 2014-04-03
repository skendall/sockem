package org.kndl.sock.algo.test

import org.scalatest.{Matchers, FlatSpec}
import org.kndl.sock.{E, V, G}
import org.kndl.sock.aglo.spf.Dijkstra

class DijkstraTest extends FlatSpec with Matchers {

  implicit def str2V(name: String) = V(name)

  "The Dijkstra.scala algorithm" should "accurately compute the shortest path" in {
    val graph = G("test")
    val from = "v1"
    val to = "v4"

    graph ++ "v1" ++ "v2" ++ "v3" ++ "v4" +| ("v1","v2",5) +| ("v1","v3",5) +| ("v2","v4",2) +| ("v3","v4",5)

    val (distance,path) = Dijkstra(graph,from,to)

    assert(path(0) == V("v1"))
    assert(path(1) == V("v2"))
    assert(path(2) == V("v4"))
  }

}