package org.kndl.sockem

case class Vertex(val name: String) extends scala.Serializable {

  private var metaData: Seq[VertexMetaData] = Seq()

  private var enabled: Boolean = true

  def set(key: String, value: Any):Unit = {
    metaData = metaData :+ VertexMetaData(key,value,System.currentTimeMillis())
  }

  def get(key: String):Option[VertexMetaData] = {
    metaData.find { vmd => vmd.key == key }
  }

  def isEnabled:Boolean = enabled

  def enable:Vertex = {
    enabled = true
    this
  }

  def disable:Vertex =  {
    enabled = false
    this
  }

  def ->(v: Vertex,w: Double): Edge = {
    Edge(this,v,w)
  }

  def <->(v: Vertex, weightTo: Double, weightFrom: Double): Set[Edge] = {
    Set(new Edge(this,v,weightTo), new Edge(v,this,weightFrom))
  }
}

