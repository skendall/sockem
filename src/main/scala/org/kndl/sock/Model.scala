package org.kndl.sock

class V(val id: Long, val name: String)
class E(val id: Long, val vA: V, val vB: V, val w: Double)
class G(val id: Long, val e: Seq[E])