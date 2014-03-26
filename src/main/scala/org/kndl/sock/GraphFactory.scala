package org.kndl.sock

import java.util.UUID

object GraphFactory {

  def createGraph(name: String, edges: Seq[E]):G = {
    new G(UUID.randomUUID(),name,edges)
  }

  def createVertex(name: String):V = {
    new V(UUID.randomUUID(),name)
  }

  def createEdge(vA: V, vB: V, w: Double):E = {
    new E(UUID.randomUUID(),vA,vB,w)
  }

}