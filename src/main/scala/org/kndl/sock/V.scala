package org.kndl.sock

case class VertexMetaData(key: String, value: Any, timestamp: Long)

case class V(val name: String) extends scala.Serializable {

  private var metaData: Seq[VertexMetaData] = Seq()

  private var enabled: Boolean = true

  def set(key: String, value: Any):Unit = {
    metaData = metaData :+ VertexMetaData(key,value,System.currentTimeMillis())
  }

  def get(key: String):Option[VertexMetaData] = {
    metaData.find { vmd => vmd.key == key }
  }

  def isEnabled:Boolean = enabled

  def enable:V = {
    enabled = true
    this
  }

  def disable:V =  {
    enabled = false
    this
  }

  def ->(v: V,w: Double): E = {
    E(this,v,w)
  }

  def <->(v: V, weightTo: Double, weightFrom: Double): Set[E] = {
    Set(new E(this,v,weightTo), new E(v,this,weightFrom))
  }

  override def equals(other: Any) = other.isInstanceOf[V] && other.asInstanceOf[V].name == name

  override def toString = name

}

