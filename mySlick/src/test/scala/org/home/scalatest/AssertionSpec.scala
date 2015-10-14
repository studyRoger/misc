package org.home.scalatest

import org.scalatest.{Tag, FlatSpec}

/**
 * Created by roger on 10/1/15.
 */
class AssertionSpec extends FlatSpec {

  "a spec " should "assert a condition expression " taggedAs (HighRisk) in {
    val a =  1
    val b = 2
    val c = a + b
    assert(c == a + b)
    assert(c == a - b)

  }

  it should "expect a value " in {
    val a  = 1
    val b = 1
    assertResult(2) {
      a + b
    }
  }

  it should "expect a exception " in {
    val s = "hi"
    intercept[IndexOutOfBoundsException] {
      s.charAt(-1)
    }
  }

  it should "have an assumption before test" in {
    val s = "12"
    assume(s.length > 3)
    assert(s.substring(0, 3).length == 3)

  }
}

object HighRisk extends Tag("com.mycompany.tags.SlowTest")