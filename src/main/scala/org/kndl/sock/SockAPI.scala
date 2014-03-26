package org.kndl.sock

import net.fwbrasil.zoot.core.Api
import net.fwbrasil.zoot.core.request.RequestMethod
import scala.concurrent.Future
import java.util.UUID

trait SockAPI extends Api {

    @endpoint(
      method = RequestMethod.POST,
      path = "/v/:name"
    )
    def create(name: String): Future[V]

    @endpoint(
      method = RequestMethod.GET,
      path = "/v/:id")
    def getById(id: UUID): Future[Option[V]]

    @endpoint(
      method = RequestMethod.GET,
      path = "/v/:name")
    def getByName(name: String): Future[Option[V]]

    @endpoint(
      method = RequestMethod.GET,
      path = "/v"
    )
    def get(): Future[Map[Long,V]]

    @endpoint(
      method = RequestMethod.POST,
      path = "/e/:idA/:idB"
    )
    def link(idA: UUID, idB: UUID): Future[Option[E]]

    @endpoint(
      method = RequestMethod.GET,
      path = "/e/:id"
    )
    def getLinks(id: UUID): Future[Seq[E]]

    @endpoint(
      method = RequestMethod.POST,
      path = "/g/:name"
    )
    def createGraph(name: String): Future[G]

    @endpoint(
      method = RequestMethod.GET,
      path = "/g/:name"
    )
    def getGraph(name: String): Future[G]

    @endpoint(
      method = RequestMethod.GET,
      path = "/g"
    )
    def getAllGraphs(): Future[Seq[String]]

    @endpoint(
      method = RequestMethod.POST,
      path = "/g/:gid/e/:eid"
    )
    def addEdge(id: UUID, eid: UUID): Future[G]

    @endpoint(
      method = RequestMethod.GET,
      path = "/g/:gid/v/:vA/v/:vB"
    )
    def findShortestPath(gid: UUID, vA: UUID, vB: UUID): Future[G]

}
