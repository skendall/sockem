package org.kndl.sock

case class V(val name: String) extends scala.Serializable {

  private var metaData:Map[String,String] = Map()

  def set(key: String, value: String):Unit = {
    metaData = metaData + (key -> value)
  }

  def get(key: String):Option[String] = {
    metaData.get(key)
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

