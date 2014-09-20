package nuno.fp.chapter5

import nuno.fp.chapter5.Exercise1.Stream
import org.scalatest.{Matchers, WordSpec}

class Exercise4Spec extends WordSpec with Matchers {
  "Exercise 4" should {
    "implement forAll" in {
      Stream() forAll {_ => true} shouldBe true
      Stream() forAll {_ => false} shouldBe true
      Stream(1, 2) forAll {_ => false} shouldBe false
      Stream(1, 2) forAll {_ => true} shouldBe true
      Stream(2, 4, 6) forAll {_ % 2 == 0} shouldBe true
      Stream(2, 4, 5) forAll {_ % 2 == 0} shouldBe false
    }
  }
}
