package org.kndl.sockem.ws

import com.twitter.finagle.builder.ServerBuilder
import com.twitter.finagle.http.Http
import java.net.InetSocketAddress
import net.fwbrasil.zoot.finagle.FinagleServer
import net.fwbrasil.zoot.core.Server
import net.fwbrasil.zoot.core.mapper.JacksonStringMapper
import scala.concurrent.ExecutionContext.Implicits.global

object SockMain extends App {

  private implicit val mirror = scala.reflect.runtime.currentMirror
  private implicit val mapper = new JacksonStringMapper
  private val port = 8080

  val server = {
    val serverBuilder =
      ServerBuilder()
        .codec(Http())
        .bindTo(new InetSocketAddress(port))
        .name("SockServer")

    new FinagleServer(Server[SockAPI](new SockService), serverBuilder.build)
  }

}
