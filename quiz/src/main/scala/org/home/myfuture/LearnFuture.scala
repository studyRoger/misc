package org.home.myfuture

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by roger on 9/12/15.
 */
object LearnFuture extends App {

  def generateFuture(a: Int, b: Int): Future[Int] = {
    Future {
      println(Thread.currentThread().getName)
      a + b
    }
  }

  def emptyFuture(name: String): Future[Unit] = {
    Future {
      println(name + " " +Thread.currentThread().getName)

    }
  }

  /*generateFuture(1, 1).onSuccess {

    case result => {
      println(s"restul is $result")
      println(Thread.currentThread().getName)
    }
  }*/

  emptyFuture("roger").foreach {
    _=> println("empty future comes" + Thread.currentThread().getName)
  }


  println("main thread ends" + Thread.currentThread().getName)


}
