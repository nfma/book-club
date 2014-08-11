package nuno.fp.chapter3

import nuno.fp.chapter3.Exercise15.concat
import org.scalatest.{Matchers, WordSpec}

class Exercise15Spec extends WordSpec with Matchers {
  "Exercise 15" should {
    "return an empty list when concatenating a list with empty list(s)" in {
      concat(List(Nil)) should be(Nil)
      concat(List(Nil, Nil)) should be(Nil)
    }

    "return a single element list when concatenating a list with a single element with empty list(s)" in {
      concat(List(List(1), Nil)) should be(List(1))
      concat(List(Nil, List(1))) should be(List(1))
    }

    "return the concatenation of several lists" in {
      concat(List(List(1), List(2))) should be(List(1, 2))
      concat(List(List(1, 2), List(3))) should be(List(1, 2, 3))
      concat(List(List(1), List(2, 3))) should be(List(1, 2, 3))
      concat(List(List(1), List(2), List(3))) should be(List(1, 2, 3))
    }
  }
}
