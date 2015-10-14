package org.home.poll.dao

import org.home.poll.Entity.{User, PollOption, Poll}

import scala.concurrent.Future

/**
 * Created by roger on 10/4/15.
 */
trait PollService {

  def createPoll(poll: Poll): Future[Poll]
  //def queryPollById(id: Int): Future[Poll]
  def queryPollByUser(user: User): Future[Seq[Poll]]
  def deactivatePollById(id: Int): Future[Int]
  def addPollOptions(poll: Poll, options: Seq[PollOption]): Future[Option[Int]]

}
