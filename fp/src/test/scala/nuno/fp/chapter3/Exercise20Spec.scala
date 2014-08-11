package nuno.fp.chapter3

import nuno.fp.chapter3.Exercise20.flatMap
import org.scalatest.{Matchers, WordSpec}

class Exercise20Spec extends WordSpec with Matchers {
  "Exercise 20" should {
    "return an empty list when flat mapping over an empty list" in {
      flatMap(Nil)(_ => Nil) should be(Nil)
    }

    "return a single element list when flat mapping over a single element list with List" in {
      flatMap(List(1))(List(_)) should be(List(1))
    }

    "return a two element list when flat mapping over a single element list with List(_, _)" in {
      flatMap(List(1))(e => List(e, e)) should be(List(1, 1))
    }

    "return a two element list when flat mapping over a single element list with List" in {
      flatMap(List(1, 1))(List(_)) should be(List(1, 1))
    }

    "make sure it complies with the example on the book" in {
      flatMap(List(1, 2, 3))(e => List(e, e)) should be(List(1, 1, 2, 2, 3, 3))
    }
  }
}
