package org.kndl.sock

import scala.slick.direct.AnnotationMapper.{column, table}

class V(val id: Long,val name: String) extends scala.Serializable
class E(val id: Long,val vA: V,val vB: V,val w: Double) extends scala.Serializable
class G(val id: Long,val name: String,val e: Seq[E]) extends scala.Serializable