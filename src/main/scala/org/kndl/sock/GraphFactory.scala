package org.kndl.sock


object GraphFactory {

  def createGraph(name: String, vertices: Seq[V], edges: Seq[E]): G = {
    new G(name, vertices, edges)
  }

  def createVertex(name: String): V = {
    new V(name)
  }

  def createEdge(vA: V, vB: V, w: Double): E = {
    new E(vA, vB, w)
  }

}