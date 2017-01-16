import java.util.UUID

import com.carforce.phantompoc.{User, MyDatabase}
import com.websudos.phantom.dsl.DateTime
import org.scalatest.FlatSpec
import org.scalatest.Matchers

import scala.concurrent.Await
import scala.concurrent.duration.Duration
import scala.util.{Success, Random, Failure}
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by borozaurus on 1/16/17.
  */
class AddUser extends FlatSpec with Matchers {
  "AddUser" should "add user and extract it" in {
    val someRandomNumber = Random.nextInt()
    val uuid = UUID.randomUUID()
    val testName = "testName" + someRandomNumber

    val user = User(uuid, "testEmail", testName, new DateTime)
    val insert = MyDatabase.users.store(user) map {
      x => assert(x.isFullyFetched)
    }

    Await.ready(insert, Duration.Inf)

    val retrivedUser = MyDatabase.users.getById(uuid) map {
      case None => fail("No user retrived")
      case Some(us) =>{
        println("User name confirmed ")
        assert(us.name == testName)}
    }

    println("Waiting for result")
    Await.ready(retrivedUser, Duration.Inf)
  }
}
