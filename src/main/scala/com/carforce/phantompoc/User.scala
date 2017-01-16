package com.carforce.phantompoc

import com.websudos.phantom.dsl.{DateTime, UUID}

/**
  * Created by borozaurus on 1/16/17.
  */
case class User (
  id: UUID,
  email: String,
  name: String,
  registrationDate: DateTime
)
