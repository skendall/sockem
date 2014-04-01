package org.kndl.sock


object GraphFactory {

  def createGraph(name: String): G = {
    new G(name)
  }

  def createVertex(graph: G, name: String): V = {
    val v = new V(name)
    graph ++ v
    v
  }

  def createEdge(graph: G, vA: V, vB: V, w: Double): E = {
    graph +| (new E(vA,vB,w))
  }

}