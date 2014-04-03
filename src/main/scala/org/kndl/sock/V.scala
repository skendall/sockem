package org.kndl.sock

class V(val name: String) extends scala.Serializable {

  def ->(v: V,w: Double): E = {
    new E(this,v,w)
  }

  def <->(v: V, weightTo: Double, weightFrom: Double): Seq[E] = {
    Seq(new E(this,v,weightTo), new E(v,this,weightFrom))
  }

  override def equals(other: Any) = other.isInstanceOf[V] && other.asInstanceOf[V].name == name

  override def toString = name

}

