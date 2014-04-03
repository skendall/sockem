package org.kndl.sock

case class E(val vA: V, val vB: V, val w: Double) extends scala.Serializable {

  override def equals(other: Any) =
    other.isInstanceOf[E] &&
      other.asInstanceOf[E].vA == vA &&
      other.asInstanceOf[E].vB == vB

  override def toString = vA + " -> " + vB + " (" + w + ")"

}