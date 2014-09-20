package nuno.fp.chapter3

import nuno.fp.chapter3.Exercise6.init
import org.scalatest.{Matchers, WordSpec}

class Exercise6Spec extends WordSpec with Matchers {
  "Exercise 6" should {
    "return an empty list for inits of empty lists" in {
      init(Nil) shouldBe Nil
    }

    "return an empty list for inits of one element lists" in {
      init(List(1)) shouldBe Nil
    }

    "return a single element list for inits of two element lists" in {
      init(List(1, 2)) shouldBe List(1)
    }

    "return a double element list for inits of three element lists" in {
      init(List(1, 2, 3)) shouldBe List(1, 2)
    }

    "answer question" in {
      println("because a single linked list needs to be traversed to get to the end")
    }
  }
}
