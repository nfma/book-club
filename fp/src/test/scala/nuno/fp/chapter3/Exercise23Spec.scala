package nuno.fp.chapter3

import nuno.fp.chapter3.Exercise22.zipSum
import nuno.fp.chapter3.Exercise23.zipWith
import org.scalatest.{Matchers, WordSpec}

class Exercise23Spec extends WordSpec with Matchers {
  "Exercise 23" should {
    "return an empty list when zipping with two empty lists" in {
      zipWith(Nil, Nil) {(_, _) => false} shouldBe Nil
    }

    "return a single element list when zipping with two single element lists" in {
      zipWith(List(1), List(1)) {_ + _} shouldBe List(2)
    }

    "return a single element list when zipping with one one element list and another with two elements" in {
      zipWith(List(1), List(1, 2)) {_ + _} shouldBe List(2)
    }

    "return a double element list when zipping with two two element lists" in {
      zipWith(List(1, 2), List(1, 2)) {_ + _} shouldBe List(2, 4)
    }

    "be a generalisation of zipSum" in {
      zipWith(List(1, 2, 3), List(4, 5, 6)) {_ + _} shouldBe zipSum(List(1, 2, 3), List(4, 5, 6))
    }
  }
}
