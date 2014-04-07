package org.kndl.sockem.test

import org.scalatest.{Matchers, FlatSpec}
import org.kndl.sockem.{G, E, V}

class GraphTest extends FlatSpec with Matchers {

  /*
   Simple tests
   */

  "A vertex" should "have a name" in {
    val v = V("test")
    assert(v.name == "test")
  }

  "An edge" should "have two vertices and a weight" in {
    val e = E( V("v1"),  V("v2"), 10)
    assert(e.vA.name == "v1")
    assert(e.vB.name == "v2")
    assert(e.w == 10)
  }

  "A graph" should "have a name and empty vertices and edges on init" in {
    val g = G("test")
    assert(g.name == "test")
    assert(g.vertices.isEmpty)
  }

  it should "allow addition of vertices" in {
    val g = G("test")
    val v1 = V("v1")
    val v2 = V("v2")
    g ++ v1
    g ++ v2
    assert(g.vertices.size == 2)
    assert(g.vertex("v1").isDefined)
    assert(g.vertex("v2").isDefined)
  }

  it should "allow addition of edges" in {
    val g = G("test")
    val v1 = V("v1")
    val v2 = V("v2")
    g ++ v1
    g ++ v2

    val e1 = E(v1, v2, 10)
    val e2 = E(v2, v1, 5)
    g +| e1
    g +| e2

    assert(g.edges(v1).size == 2)
    assert(g.edges(v2).size == 2)
    assert(g.edge(v1,v2).isDefined)
    assert(g.edge(v1,v2).get.w == 10)
    assert(g.edge(v2,v1).get.w == 5)
  }

  it should "allow removal of edges" in {
    val g = G("test")
    val v1 = V("v1")
    val v2 = V("v2")
    g ++ v1
    g ++ v2

    val e1 = v1 -> (v2, 10)
    val e2 = v2 -> (v1, 5)

    g +| e1
    g +| e2

    assert(g.edges(v1).size == 2)
    assert(g.edges(v2).size == 2)

    g -| e1

    assert(g.edges(v1).size == 1)
    assert(g.edges(v2).size == 1)
  }

  it should "allow modification of edges" in {
    val g = G("test")
    val v1 = V("v1")
    val v2 = V("v2")

    g ++ v1
    g ++ v2

    g +| (v1,v2,10)

    assert(g.edge(v1,v2).get.w == 10)

    g +| (v1,v2,5)

    assert(g.edge(v1,v2).get.w == 5)
  }

  it should "calculate the shortest path correctly" in {
    val g = G("test")
    val v1 = V("v1")
    val v4 = V("v4")

    g ++ v1 ++ V("v2") ++ V("v3") ++ v4 +| ("v1","v2",5) +| ("v1","v3",5) +| ("v3","v4",2) +| ("v2","v4",5)

    val (dist,path) = g ~ (v1,v4)

    assert(dist == 7)
    assert(path(0) == v1)
    assert(path(1) == V("v3"))
    assert(path(2) == V("v4"))
  }

  it should "visit all nodes and successfully interact with each node" in {
    val g = G("test")
    val v1 = V("v1")
    val v4 = V("v4")

    g ++ v1 ++ V("v2") ++ V("v3") ++ v4 +| ("v1","v2",5) +| ("v1","v3",5) +| ("v3","v4",2) +| ("v2","v4",5)

    val visited = g.visitAll(v1) { v => v.set("visited","true") }

    for(v <- g.vertices) assert(v.get("visited").isDefined && v.get("visited").get == "true")
  }

}
