package org.home.poll.dao.impl

import org.home.poll.Entity.{User, PollOption, Poll}
import org.home.poll.Schema.{PollOptionTable, PollTable}
import org.home.poll.dao.PollService

import slick.driver.H2Driver.api._
import slick.lifted.TableQuery

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


/**
 * Created by roger on 10/4/15.
 */
trait PollServiceComponent { this: PollDatabase =>
  val polls: TableQuery[PollTable] = TableQuery[PollTable]
  val pollOptions: TableQuery[PollOptionTable] = TableQuery[PollOptionTable]

  val pollService: PollService

  class PollServiceImpl extends PollService {

    override def createPoll(poll: Poll): Future[Poll] = {
      val pollRow = (0, poll.question, poll.owner.id, "active")
      val insertion= (polls returning polls.map(_.id)) += pollRow
      println(insertion.statements)

      val insertPollResult: Future[Int] = db.run(insertion)

      val insertOptionsResult = insertPollResult.flatMap(createPollOptions(_, poll.options))


      for {
        pid <- insertPollResult
        count <- insertOptionsResult
        if count.isDefined && count.get == poll.options.length
      } yield Poll(pid, poll.question, poll.owner, poll.options, "active")

    }

    def createPollOptions(pollId: Int, options: Seq[PollOption]): Future[Option[Int]] = {
      val rows = options.map { it =>
        (-1, it.value, pollId)

      }
      val insertion = pollOptions ++= rows
      println(insertion.statements)
      db.run(insertion)
    }

    override def addPollOptions(poll: Poll, options: Seq[PollOption]): Future[Option[Int]] = {

      val insertRows = options.map { op =>
        (-1, op.value, poll.id)
      }
      val insertion = pollOptions ++= insertRows
      db.run(insertion)

    }

    def queryPollByUser(user: User): Future[Seq[Poll]] = {
      val query = polls.filter(_.ownerId === user.id)
      val queryResult = db.run(query.result)
      queryResult flatMap { pollRows =>
        val polls: Seq[Future[Poll]] = pollRows map { pollRow =>
          queryPollOptionByPollId(pollRow._1) map { pollOptions =>
            Poll(pollRow._1, pollRow._2, user, pollOptions, pollRow._4)
          }
        }
        Future.sequence(polls)
      }
    }

    def queryPollOptionByPollId(pid: Int): Future[Seq[PollOption]] = {
      val query = pollOptions.filter(_.pollId === pid)
      val queryResult = db.run(query.result)
      queryResult map { pollOptionRows =>
        pollOptionRows map { pollOptionRow =>
          PollOption(pollOptionRow._1, pollOptionRow._2)

        }

      }
    }

    override def deactivatePollById(id: Int): Future[Int] = {
      val update =  polls.filter(_.id === id).map(_.status).update("inactive")
      db.run(update)

    }

    /*override def queryPollById(id: Int): Future[Poll] = {
      val queryPoll = polls.filter(_.id === id)
      val queryPollOptions = pollOptions.filter(_.pollId === id)
      val pollResult = db.run(queryPoll.result)
      val pollOptionResult = db.run(queryPollOptions.result)

      for {
        pollRow <- pollResult
        pollOptionRows <- pollOptionResult
      } yield {
        val pollOptions = pollOptionRows.map { r =>
          PollOption(r._1, r._2)
        }
        pollRow.map { r =>
          Poll(r._1, r._2,)
        }

      }
    }*/


  }
}
