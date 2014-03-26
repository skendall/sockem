package org.kndl.sock


class V(val id: String, val name: String) extends scala.Serializable

class E(val id: String, val vA: V, val vB: V, val w: Double) extends scala.Serializable

class G(val id: String, val name: String, val e: Seq[E]) extends scala.Serializable