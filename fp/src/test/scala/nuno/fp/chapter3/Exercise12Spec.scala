package nuno.fp.chapter3

import nuno.fp.chapter3.Exercise12.reverse
import org.scalatest.{Matchers, WordSpec}

class Exercise12Spec extends WordSpec with Matchers {
  "Exercise 12" should {
    "return an empty list as the reverse of an empty list" in {
      reverse(Nil) shouldBe Nil
    }

    "return a single element list as the reverse of a single element list" in {
      reverse(List(1)) shouldBe List(1)
    }

    "return a reversed two element list as the reverse of a two element list" in {
      reverse(List(1, 2)) shouldBe List(2, 1)
    }

    "return a reversed three element list as the reverse of a three element list" in {
      reverse(List(1, 2, 3)) shouldBe List(3, 2, 1)
    }
  }
}
