package com.carforce.phantompoc

import com.websudos.phantom.dsl._
/**
  * Created by borozaurus on 1/16/17.
  */
object Defaults {

  val hosts = Seq("127.0.0.1")

  val connector = ContactPoints(hosts).keySpace("poc")
}