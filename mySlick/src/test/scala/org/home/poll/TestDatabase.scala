package org.home.poll


import org.home.poll.dao.impl.PollDatabase

import slick.driver.H2Driver.api._

/**
 * Created by roger on 10/5/15.
 */
trait TestDatabase extends PollDatabase {
  val db = Database.forConfig("memPoll")
}
