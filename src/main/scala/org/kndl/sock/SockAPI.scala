package org.kndl.sock

import net.fwbrasil.zoot.core.Api
import net.fwbrasil.zoot.core.request.RequestMethod
import scala.concurrent.Future

trait SockAPI extends Api {

  // Graph methods

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

  // Vertex methods

  @endpoint(
    method = RequestMethod.POST,
    path = "/g/:gname/v/:vname"
  )
  def createVertex(gname: String, vname: String): Future[Option[V]]

  @endpoint(
    method = RequestMethod.GET,
    path = "/g/:gname/v/:vname")
  def getVertex(gname: String, vname: String): Future[Option[V]]

  @endpoint(
    method = RequestMethod.GET,
    path = "/g/:name/v"
  )
  def getVertices(gname: String): Future[Map[Long, V]]

  // Edge methods

  @endpoint(
    method = RequestMethod.GET,
    path = "/g/:gname/v/:vname/e"
  )
  def getEdges(gname: String, vname: String)

  @endpoint(
    method = RequestMethod.POST,
    path = "/g/:gname/e/:vname1/:vname2/:weight"
  )
  def addEdge(gname: String, vname1: String, vname2: String, weight: Double): Future[G]

  @endpoint(
    method = RequestMethod.GET,
    path = "/g/:gid/v/:vname1/v/:vname2"
  )
  def findShortestPath(gid: String, vA: String, vB: String): Future[G]

}
