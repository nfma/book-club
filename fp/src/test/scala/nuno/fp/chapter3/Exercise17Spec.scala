package nuno.fp.chapter3

import nuno.fp.chapter3.Exercise17.doubleToString
import org.scalatest.{Matchers, WordSpec}

class Exercise17Spec extends WordSpec with Matchers {
  "Exercise 16" should {
    "return an empty list when converting an empty list" in {
      doubleToString(Nil) should be(Nil)
    }

    "return the string of the sole element when converting a single element list" in {
      doubleToString(List(1.0)) should be(List("1.0"))
    }

    "return a list with all elements converted to string" in {
      doubleToString(List(1.0, 2.0)) should be(List("1.0", "2.0"))
      doubleToString(List(1.0, 2.0, 3.0)) should be(List("1.0", "2.0", "3.0"))
    }
  }

}
