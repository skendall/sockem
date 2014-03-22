package org.kndl.sock

import scala.concurrent.Future

class SockService extends SockAPI {
  def getMe() = Future {
    1
  }
}
