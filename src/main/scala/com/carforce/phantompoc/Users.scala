package com.carforce.phantompoc

import scala.concurrent.Future
import com.websudos.phantom.dsl._

/**
  * Created by borozaurus on 1/16/17.
  */
class Users extends CassandraTable[ConcreteUsers, User] {

  object id extends UUIDColumn(this) with PartitionKey[UUID]
  object email extends StringColumn(this)
  object name extends StringColumn(this)
  object registrationdate extends DateTimeColumn(this)

  def fromRow(row: Row): User = {
    User(
      id(row),
      email(row),
      name(row),
      registrationdate(row)
    )
  }
}

abstract class ConcreteUsers extends Users with RootConnector {

  def store(user: User): Future[ResultSet] = {
    insert.value(_.id, user.id).value(_.email, user.email)
      .value(_.name, user.name)
      .value(_.registrationdate, user.registrationDate)
      .consistencyLevel_=(ConsistencyLevel.ALL)
      .future()
  }

  def getById(id: UUID): Future[Option[User]] = {
    select.where(_.id eqs id).one()
  }
}
