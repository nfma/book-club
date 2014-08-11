package nuno.fp.chapter3

import nuno.fp.chapter3.Exercise1.r
import org.scalatest.{Matchers, WordSpec}

class Exercise1Spec extends WordSpec with Matchers {
  "Exercise 1" should {
    "answer the question" in {
      r should be(3)
    }
  }
}
