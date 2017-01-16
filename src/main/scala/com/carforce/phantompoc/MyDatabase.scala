package com.carforce.phantompoc

import com.websudos.phantom.dsl._
/**
  * Created by borozaurus on 1/16/17.
  */

class MyDatabase(val keyspace: KeySpaceDef) extends Database(keyspace) {
  object users extends ConcreteUsers with keyspace.Connector
}

object MyDatabase extends MyDatabase(Defaults.connector)