package org.kndl.sock

import scala.slick.direct.AnnotationMapper.{column, table}
import java.util.UUID

class V(val id: UUID,val name: String) extends scala.Serializable
class E(val id: UUID,val vA: V,val vB: V,val w: Double) extends scala.Serializable
class G(val id: UUID,val name: String,val e: Seq[E]) extends scala.Serializable