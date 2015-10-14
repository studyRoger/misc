package org.home.poll

import org.home.poll.Entity.User
import org.home.poll.dao.UserService
import org.home.poll.dao.impl.{UserServiceComponent, PollDatabase}
import org.scalatest.FlatSpec

import slick.driver.H2Driver.api._

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by roger on 10/4/15.
 */
class UserServiceSpec extends FlatSpec with UserServiceComponent with TestDatabase {

  val userService:UserService = new UserServiceImpl()

  val setup:Future[Unit] = db.run(users.schema.create)

  val userToCreate = User(-1, "oracle", "com", 10, "active")
  val userExisting = User(1, "oracle", "com", 10, "active")
  val userNotExisting = User(11, "oracle", "com", 10, "active")
  val userToUpdate = User(1, "java", "org", 15, "active")

  "user service" can "create a new user" in {

    val f  = setup.flatMap { _ =>
      userService.createUser(userToCreate)
    } map { u =>
      assert(u.id > 0, "auto generated id should > 0")
      assert(u.firstName === userToCreate.firstName)
      assert(u.lastName === userToCreate.lastName)
      assert(u.age === userToCreate.age)
    }
    Await.result(f, Duration.Inf)
  }

  it can "query a user by id" in {
    val f  = setup.flatMap { _ =>
      userService.getUserById(userExisting.id)
    } map { op =>
      assert(op.isDefined, "query existing user but return empty")
      val userResult = op.get
      assert(userResult === userExisting, "the user is not inserted to the db")
    }
    Await.result(f, Duration.Inf)
  }

  it can "update an existing user by a given user" in {
    val f  = setup.flatMap { _ =>
      userService.updateUser(userToUpdate)
    } map { count =>
      assert(count === 1, "affected row count should be 1, after a user is updated")

    } flatMap { _ =>
      userService.getUserById(userToUpdate.id)
    } map { op =>
        assert(op.isDefined, "query existing user but return empty")
        val userResult = op.get
        assert(userResult === userToUpdate, "the user in the DB is not updated")

    }
    Await.result(f, Duration.Inf)
  }

  it should "update nothing if the user is not existing" in {
    val f  = setup.flatMap { _ =>
      userService.updateUser(userNotExisting)
    } map { count =>
      assert(count === 0, "affected row count should be 0, when client updates a non-existent user")

    }
    Await.result(f, Duration.Inf)
  }

  it should "delete a user by the given user id" in {
    val f  = setup.flatMap { _ =>
      userService.deleteUser(userExisting)
    } map { count =>
      assert(count === 1, "affected row count should be 1, when client deletes a existing user")

    } flatMap { _ =>
      userService.getUserById(userExisting.id)
    } map { op =>
      assert(op.isEmpty, "query non-existent user should return None")


    }
    Await.result(f, Duration.Inf)
  }

  it should "delete a nothing if the given user is non-existent" in {
    val f  = setup.flatMap { _ =>
      userService.deleteUser(userExisting)
    } map { count =>
      assert(count === 0, "affected row count should be 0, when client deletes a non-existent user")

    }
    Await.result(f, Duration.Inf)
  }

}
