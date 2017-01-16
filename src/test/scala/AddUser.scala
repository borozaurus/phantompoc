import java.util.UUID

import com.carforce.phantompoc.{User, MyDatabase}
import com.websudos.phantom.dsl.DateTime
import org.scalatest.FlatSpec
import org.scalatest.Matchers

import scala.util.{Success, Random, Failure}
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by borozaurus on 1/16/17.
  */
class AddUser extends FlatSpec with Matchers {
  "AddUser" should "add user and extract it" in {
    val someRandomNumber = Random.nextInt()
    val uuid = UUID.randomUUID()
    var testName = "testName" + someRandomNumber

    val user = User(uuid, "testEmail", testName, new DateTime)
    var insert = MyDatabase.users.store(user)

    insert onComplete {
      case Failure(t) => fail("Fail to persist" )
      case _ =>
    }

    insert wait

    var retrivedUser = MyDatabase.users.getById(uuid)

    retrivedUser onSuccess  {
      case None => fail("No user retrived")
      case Some(us) => assert(us.name == testName)
    }

    retrivedUser wait
  }
}
