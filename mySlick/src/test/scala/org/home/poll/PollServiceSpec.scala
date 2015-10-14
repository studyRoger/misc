package org.home.poll

import org.home.poll.Entity.{PollOption, Poll, User}
import org.home.poll.dao.{PollService, UserService}
import org.home.poll.dao.impl.{PollServiceComponent, UserServiceComponent}
import org.scalatest.FlatSpec

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import slick.driver.H2Driver.api._

import scala.util.{Failure, Success}

/**
 * Created by roger on 9/30/15.
 */


class PollServiceSpec extends FlatSpec with UserServiceComponent with PollServiceComponent with TestDatabase {

  val userService: UserService = new UserServiceImpl()
  val pollService: PollService = new PollServiceImpl()

  val userOracle = User(1, "oracle", "com", 10, "active")

  val setupActions = DBIO.seq(
    (users.schema ++ polls.schema ++ pollOptions.schema).create,

    users ++= userOracle :: Nil map { u =>
      (u.id, u.firstName, u.lastName, u.age, "active")
    }

  )

  val setup:Future[Unit] = db.run(setupActions)

  val optionsToCreate = PollOption(1, "java") :: PollOption(2, "c++") :: PollOption(3, "python") :: PollOption(4, "scala") :: Nil
  val pollToCreate = Poll(1, "what is your favorite language", userOracle, optionsToCreate, "active")

  "poll service" can "create poll with the poll owner" in  {
    val f = setup flatMap { _ =>


      pollService.createPoll(pollToCreate)
    } map { poll =>
      assert(poll.question === pollToCreate.question)
      poll.options.zip(optionsToCreate).foreach { it =>
        assert(it._1.value === it._2.value)
      }
    }
    Await.result(f, Duration.Inf)
  }

  it can "query all the polls by a given owner" in {
    val f = setup flatMap { _ =>
      pollService.queryPollByUser(userOracle)
    } map { polls =>
      polls.foreach { it =>
        assert(it.question === pollToCreate.question)
        it.options.zip(optionsToCreate).foreach { it =>
          assert(it._1.value === it._2.value)
        }
      }
    }
    Await.result(f, Duration.Inf)
  }

}