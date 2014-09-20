package nuno.fp.chapter3

import nuno.fp.chapter3.Exercise16.addOne
import org.scalatest.{Matchers, WordSpec}

class Exercise16Spec extends WordSpec with Matchers {
  "Exercise 16" should {
    "return an empty list when adding one to all elements of an empty list" in {
      addOne(Nil) shouldBe Nil
    }

    "return the sole element incremented when adding one to a single element list" in {
      addOne(List(1)) shouldBe List(2)
    }

    "return a list with all elements incremented" in {
      addOne(List(1, 2)) shouldBe List(2, 3)
      addOne(List(1, 2, 3)) shouldBe List(2, 3, 4)
    }
  }
}
