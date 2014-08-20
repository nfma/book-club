package nuno.fp.chapter5

import nuno.fp.chapter5.Exercise1.Stream
import org.scalatest.{Matchers, WordSpec}

class Exercise4Spec extends WordSpec with Matchers {
  "Exercise 4" should {
    "implement forAll" in {
      Stream().forAll(_ => true) should be(true)
      Stream().forAll(_ => false) should be(true)
      Stream(1, 2).forAll(_ => false) should be(false)
      Stream(1, 2).forAll(_ => true) should be(true)
      Stream(2, 4, 6).forAll(_ % 2 == 0) should be(true)
      Stream(2, 4, 5).forAll(_ % 2 == 0) should be(false)
    }
  }
}
