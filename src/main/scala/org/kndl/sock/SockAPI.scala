package org.kndl.sock

import net.fwbrasil.zoot.core.Api
import net.fwbrasil.zoot.core.request.RequestMethod
import scala.concurrent.Future

trait SockAPI extends Api {

    @endpoint(
      method = RequestMethod.GET,
      path = "/getme")
    def getMe(): Future[Int]

}
