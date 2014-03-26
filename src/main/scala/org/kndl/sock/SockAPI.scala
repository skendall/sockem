package org.kndl.sock

import net.fwbrasil.zoot.core.Api
import net.fwbrasil.zoot.core.request.RequestMethod
import scala.concurrent.Future

trait SockAPI extends Api {

    @endpoint(
      method = RequestMethod.POST,
      path = "/g/:gname/v/:vname"
    )

    @endpoint(
      method = RequestMethod.GET,
      path = "/g/:gname/v/:vname")
    def getByName(name: String): Future[Option[V]]

    @endpoint(
      method = RequestMethod.GET,
      path = "/g/:name/v"
    )
    def get(): Future[Map[Long,V]]

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
    def addEdge(id: String, eid: String): Future[G]

    @endpoint(
      method = RequestMethod.GET,
      path = "/g/:gid/v/:vA/v/:vB"
    )
    def findShortestPath(gid: String, vA: String, vB: String): Future[G]

}
