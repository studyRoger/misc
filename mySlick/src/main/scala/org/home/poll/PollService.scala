package org.home.poll


import org.home.poll.Entity.{User, Poll}


import java.sql.Timestamp
import java.time.Instant


import org.home.poll.Schema._
import slick.driver.H2Driver.api._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}
import scala.util.{Failure, Success}


/**
 * Created by roger on 9/30/15.
 */
class PollServicex(val db: Database) {

  //val db = Database.forConfig("poll")
  val users: TableQuery[UserTable] = TableQuery[UserTable]
  val polls: TableQuery[PollTable] = TableQuery[PollTable]
  val options: TableQuery[PollOptionTable] = TableQuery[PollOptionTable]
  val votes: TableQuery[VoteTable] = TableQuery[VoteTable]

  def init(): Future[Unit] = {
    val setupAction: DBIO[Unit] = DBIO.seq(
      // Create the schema by combining the DDLs
      // tables using the query interfaces
      (users.schema ++ polls.schema ++ options.schema ++ votes.schema).create,

      // Insert some data
      users += (1, "oracle", "com", 30, "Active"),
      users += (2, "google", "com", 20, "Active"),
      users += (3, "IBM", "com", 90, "Active"),
      users += (4, "Typesafe", "com", 2, "Active"),
      users += (5, "Pivotal", "com", 10, "Active"),

      polls += (1, "what is your favorite programming language?", 1, "Active"),

      options += (1, "java", 1),
      options += (2, "c++", 1),
      options += (3, "python", 1),
      options += (4, "scala", 1),

      votes += (1, 1, 1, Timestamp.from(Instant.now())),
      votes += (2, 2, 3, Timestamp.from(Instant.now())),
      votes += (3, 3, 2, Timestamp.from(Instant.now())),
      votes += (4, 4, 4, Timestamp.from(Instant.now())),
      votes += (5, 5, 1, Timestamp.from(Instant.now()))
    )
    db.run(setupAction)
  }

  def getPollById(id: Int):Future[Option[(Int, String, String, Int, String, String, Int, String)]] = {
    val query = for (
      (p, u) <- polls join users on (_.ownerId === _.id) filter (_._1.id === id)
    ) yield (p.id, p.question, p.status, u.id, u.firstName, u.lastName, u.age, u.status)
    db.run(query.result).map(_.headOption)

  }

  def getUserById(id: Int):Future[Option[User]] = {
    val query = users.filter(_.id === id)
    db.run(query.result).map(_.headOption.map(t => User(t._1, t._2, t._3, t._4, t._5)))
  }

  def addUser(user: User): Future[User] = {
    val row = (0, user.firstName, user.lastName, user.age, "Active")
    val insertion = (users returning users.map(_.id)) += row
    db.run(insertion).map({ id =>
      User(id, user.firstName, user.lastName, user.age, "active")
    })

  }

  def main(args: Array[String]): Unit = {
    /* val db = Database.forConfig("poll")
   val service  = new PollService(db)

   val f = service.getPollById(1)
  f.onComplete {
     case Success(opPoll) => {
       val poll = opPoll map { t: (Int, String, String, Int, String, String, Int, String) =>
         val owner = User(t._4, t._5, t._6, t._7)
         Poll(t._1, t._2, owner)
       } getOrElse {
         println("nothing found")

       }
       println(poll)
     }
     case Failure(exception) => println(exception)
   }*/





    //Await.result(f, Duration.Inf)
  }
}
