package nuno.fp.chapter5

import nuno.fp.chapter5.Exercise1.Stream
import org.scalatest.{Matchers, WordSpec}

class Exercise1Spec extends WordSpec with Matchers {
  "Exercise 1" should {
    "implement toList" in {
      Stream().toList shouldBe Nil
      Stream(1).toList shouldBe List(1)
      Stream(1, 2).toList shouldBe List(1, 2)
      Stream(1, 2, 3, 4, 5).toList shouldBe List(1, 2, 3, 4, 5)
    }
  }
}
