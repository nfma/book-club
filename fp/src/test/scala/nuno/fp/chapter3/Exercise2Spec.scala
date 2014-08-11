package nuno.fp.chapter3

import nuno.fp.chapter3.Exercise2.tail
import org.scalatest.{Matchers, WordSpec}

class Exercise2Spec extends WordSpec with Matchers {
  "Exercise 2" should {
    "return nil as the tail of the empty list" in {
      tail(Nil) should be(Nil)
    }

    "return nil as the tail of a single element list" in {
      tail(List(1)) should be(Nil)
    }

    "return a single element list as the tail of a list with two elements" in {
      tail(List(1, 2)) should be(List(2))
    }
  }
}
