package nuno.fp.chapter3

import nuno.fp.chapter3.Exercise10.foldLeft
import org.scalatest.{Matchers, WordSpec}

class Exercise10Spec extends WordSpec with Matchers {
  "Exercise 10" should {
    "fold nil with an initial value for the accumulator returns the initial value" in {
      foldLeft[Int, Int](Nil, 1) {_ + _} shouldBe 1
    }

    "fold an element should return the result of applying the function" in {
      foldLeft[Int, Int](List(1), 1) {_ + _} shouldBe 2
    }

    "fold a two element should return the result of applying the function twice to the three elements" in {
      foldLeft[Int, Int](List(1, 2), 1) {_ + _} shouldBe 4
    }
  }
}
