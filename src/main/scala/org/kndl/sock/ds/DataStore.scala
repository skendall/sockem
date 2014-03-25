package org.kndl.sock.ds

import java.util.concurrent.atomic.AtomicInteger


object DataStore {

  val id: AtomicInteger = new AtomicInteger(1)

  Database.forURL

  val map = PersistentMap.create[String,String]("graph", database)

}