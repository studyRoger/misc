package org.home.poll

import java.sql.Timestamp

import slick.driver.H2Driver.api._



/**
 * Created by roger on 9/30/15.
 */
object Schema {

  class UserTable(tag: Tag) extends Table[(Int, String, String, Int, String)](tag, "USER") {
    def id = column[Int]("USER_ID", O.PrimaryKey, O.AutoInc) // This is the primary key column
    def firstName = column[String]("FIRST_NAME")
    def lastName = column[String]("LAST_NAME")
    def age = column[Int]("AGE")
    def status = column[String]("STATUS")
    // Every table needs a * projection with the same type as the table's type parameter
    def * = (id, firstName, lastName, age, status)
  }

  class PollTable(tag: Tag) extends Table[(Int, String, Int, String)](tag, "POLL") {
    def id = column[Int]("POLL_ID", O.PrimaryKey, O.AutoInc)
    def question = column[String]("QUESTION")
    def ownerId = column[Int]("OWNER_ID")
    def status = column[String]("STATUS")

    def * = (id, question, ownerId, status)

    // A reified foreign key relation that can be navigated to create a join
    def ownerFK = foreignKey("OWNER_FK", ownerId, TableQuery[UserTable])(_.id)


  }

  class PollOptionTable(tag: Tag) extends Table[(Int, String, Int)](tag, "OPTION") {
    def id = column[Int]("OPTION_ID", O.PrimaryKey, O.AutoInc)
    def value = column[String]("VALUE")
    def pollId = column[Int]("POLL_ID")

    def * = (id, value, pollId)

    def pollFK = foreignKey("POLL_FK", pollId, TableQuery[PollTable])(_.id)

  }

  class VoteTable(tag: Tag) extends Table[(Int, Int, Int, Timestamp)](tag, "VOTE") {
    def id = column[Int]("VOTE_ID", O.PrimaryKey, O.AutoInc)
    def userId = column[Int]("USER_ID")
    def optionID = column[Int]("OPTION_ID")
    def createdDate = column[Timestamp]("CREATED_DATE")

    def * = (id, userId, optionID, createdDate)

    def optionFK = foreignKey("OPTION_ID", optionID, TableQuery[PollOptionTable])(_.id)
    def userFK = foreignKey("USER_ID", userId, TableQuery[UserTable])(_.id)

  }

}
