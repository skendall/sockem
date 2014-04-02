package org.kndl.sock.test

import org.scalatest.{Matchers, FlatSpec}
import org.kndl.sock.{G, E, V}

class GraphTest extends FlatSpec with Matchers {

  /*
   Simple tests
   */

  "A vertex" should "have a name" in {
    val v = new V("test")
    assert(v.name == "test")
  }

  "An edge" should "have two vertexs and a weight" in {
    val e = new E(new V("v1"), new V("v2"), 10)
    assert(e.vA.name == "v1")
    assert(e.vB.name == "v2")
    assert(e.w == 10)
  }

  "A graph" should "have a name and empty vertices and edges on init" in {
    val g = new G("test")
    assert(g.name == "test")
    assert(g.vertices.isEmpty)
  }
}
