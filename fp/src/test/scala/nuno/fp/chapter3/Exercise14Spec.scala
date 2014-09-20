package nuno.fp.chapter3

import nuno.fp.chapter3.Exercise14.append
import org.scalatest.{Matchers, WordSpec}

class Exercise14Spec extends WordSpec with Matchers {
  "Exercise 14" should {
    "return an empty list when appending two empty lists" in {
      append(Nil, Nil) shouldBe Nil
    }

    "return the first list when appending with an empty list" in {
      append(List(1), Nil) shouldBe List(1)
    }

    "return the second list when appending with an empty list" in {
      append(Nil, List(1)) shouldBe List(1)
    }

    "return the appending of the two lists when appending two non empty lists" in {
      append(List(1), List(2)) shouldBe List(1, 2)
      append(List(1), List(2, 3)) shouldBe List(1, 2, 3)
      append(List(1, 2), List(3)) shouldBe List(1, 2, 3)
    }
  }
}
