package nuno.fp.chapter3

import nuno.fp.chapter3.Exercise19.filter
import org.scalatest.{Matchers, WordSpec}

class Exercise19Spec extends WordSpec with Matchers {
  "Exercise 19" should {
    "return an empty list when filtering an empty list" in {
      filter(Nil) {_ => true} shouldBe Nil
    }

    "return an empty list when filtering a single element list with false" in {
      filter(List(1)) {_ => false} shouldBe Nil
    }

    "return the original list when filtering a single element list with true" in {
      filter(List(1)) {_ => true} shouldBe List(1)
    }

    "return a list filtered when filtering a multi element list" in {
      filter(List(1, 2, 3, 4, 5, 6)) {_ % 2 == 0} shouldBe List(2, 4, 6)
    }
  }
}
