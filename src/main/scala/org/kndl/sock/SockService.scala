package org.kndl.sock

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class SockService extends SockAPI {
  def getMe() = Future {
    1
  }
}
