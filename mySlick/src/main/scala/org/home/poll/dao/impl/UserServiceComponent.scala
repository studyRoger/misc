package org.home.poll.dao.impl

import org.home.poll.Entity.User
import org.home.poll.Schema.UserTable
import org.home.poll.dao.UserService

import slick.driver.H2Driver.api._
import slick.lifted.TableQuery

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
 * Created by roger on 10/4/15.
 */
trait UserServiceComponent { this: PollDatabase =>
  val users: TableQuery[UserTable] = TableQuery[UserTable]
  val userService: UserService

  class UserServiceImpl extends UserService {

    override def createUser(user: User): Future[User] = {
      val row = (0, user.firstName, user.lastName, user.age, "Active")
      val insertion = (users returning users.map(_.id)) += row
      db.run(insertion).map({ id =>
        User(id, user.firstName, user.lastName, user.age, "active")
      })

    }

    override def getUserById(id: Int):Future[Option[User]] = {
      val query = users.filter(_.id === id)
      db.run(query.result).map(_.headOption.map(t => User(t._1, t._2, t._3, t._4, t._5)))
    }



    override def updateUser(user: User): Future[Int] = {
      val query = users.filter(_.id === user.id).map(r => (r.firstName, r.lastName, r.age))
      val action = query.update(user.firstName, user.lastName, user.age)
      db.run(action)
    }

    override def deleteUser(user: User): Future[Int] = {
      val query = users.filter(_.id === user.id)
      db.run(query.delete)
    }
  }

}
