package org.kndl.sock

import java.util.UUID

object GraphFactory {

  def createGraph(name: String, edges: Seq[E]):G = {
    new G(UUID.randomUUID().toString,name,edges)
  }

  def createVertex(name: String):V = {
    new V(UUID.randomUUID().toString,name)
  }

  def createEdge(vA: V, vB: V, w: Double):E = {
    new E(UUID.randomUUID().toString,vA,vB,w)
  }

}