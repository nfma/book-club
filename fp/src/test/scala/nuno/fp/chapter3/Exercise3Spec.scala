package nuno.fp.chapter3

import nuno.fp.chapter3.Exercise3.setHead
import org.scalatest.{Matchers, WordSpec}

class Exercise3Spec extends WordSpec with Matchers {
  "Exercise 3" should {
    "add an element when setting the head of an empty list" in {
      setHead(1, Nil) shouldBe List(1)
    }

    "replace the head element when setting the head of a single element list" in {
      setHead(1, List(2)) shouldBe List(1)
    }

    "replace the head element when setting the head of a double element list" in {
      setHead(1, List(2, 2)) shouldBe List(1, 2)
    }
  }
}
