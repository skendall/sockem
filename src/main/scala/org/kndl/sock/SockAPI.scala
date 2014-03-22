package org.kndl.sock

import net.fwbrasil.zoot.core.Api
import net.fwbrasil.zoot.core.request.RequestMethod
import scala.concurrent.Future
import org.kndl.sock.model.{SockLink, Sock}

trait SockAPI extends Api {

    @endpoint(
      method = RequestMethod.POST,
      path = "/create/:name"
    )
    def create(name: String): Future[Sock]

    @endpoint(
      method = RequestMethod.GET,
      path = "/get/:id")
    def getById(id: Int): Future[Option[Sock]]

    @endpoint(
      method = RequestMethod.GET,
      path = "/getByName/:name")
    def getByName(name: String): Future[Option[Sock]]

    @endpoint(
      method = RequestMethod.GET,
      path = "/get"
    )
    def get(): Future[Map[Int,Sock]]

    @endpoint(
      method = RequestMethod.GET,
      path = "/link/:idA/:idB"
    )
    def link(idA: Int, idB: Int): Future[Option[SockLink]]

    @endpoint(
      method = RequestMethod.GET,
      path = "/links/:id"
    )
    def getLinks(id: Int): Future[Seq[SockLink]]
}
