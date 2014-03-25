package org.kndl.sock

import scala.slick.direct.AnnotationMapper.{column, table}

@table("V")class V(
   @column("id")val id: Long,
   @column("name")val name: String
)

@table("E")class E(
   @column("id")val id: Long,
   @column("vA")val vA: V,
   @column("vB")val vB: V,
   @column("w")val w: Double
)

@table("G")class G(
   @column("id")val id: Long,
   @column("e")val e: Seq[E]
)