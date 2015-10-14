package org.home.poll

import java.util.Date

/**
 * Created by roger on 9/30/15.
 */
object Entity {

  case class User(id: Int, firstName: String, lastName: String, age: Int, status: String)
  case class Poll(id: Int, question: String, owner: User, options: Seq[PollOption], status: String)
  case class PollOption(id: Int, value: String)
  case class Vote(id: Int, user: User, option: PollOption, date: Date)

}
