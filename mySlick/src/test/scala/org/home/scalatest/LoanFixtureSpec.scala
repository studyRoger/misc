package org.home.scalatest

import org.scalatest.FlatSpec

/**
 * Created by roger on 10/1/15.
 */
class LoanFixtureSpec extends FlatSpec {

  def withSomeResource(test: (String, Int) => Any): Unit = {
    println("there is a String")
    println("and a Int")
    try {
      test("a String", 1)
    } finally {
      println("clean up the String")
      println("and the Int")
    }
  }

  "each test" should "have some setup and teardown" in withSomeResource { (str, i) =>
    assume(!str.isEmpty)
    assume(i != 0)
    assert(1 == 1)
    println("in a test with setup and teardown")
  }

  it should "have setup and teardown every time" in withSomeResource { (str, i) =>
    assume(!str.isEmpty)
    assume(i != 0)
    assert(1 == 1)
    println("in a test with setup and teardown")
  }
}
