package org.home.poll.dao

import org.home.poll.Entity.User
import org.home.poll.Schema.UserTable
import slick.lifted.TableQuery

import scala.concurrent.Future

/**
 * Created by roger on 10/4/15.
 */
trait UserService {


  def createUser(user: User): Future[User]
  def getUserById(id: Int): Future[Option[User]]
  def updateUser(user: User): Future[Int]
  def deleteUser(user: User): Future[Int]

}
