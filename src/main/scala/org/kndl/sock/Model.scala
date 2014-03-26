package org.kndl.sock


class V(val name: String) extends scala.Serializable

class E(val vA: V, val vB: V, val w: Double) extends scala.Serializable

class G(val name: String, val v: Seq[V], val e: Seq[E]) extends scala.Serializable