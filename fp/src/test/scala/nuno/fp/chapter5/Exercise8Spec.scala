package nuno.fp.chapter5

import nuno.fp.chapter5.Exercise1.Stream.constant
import nuno.fp.chapter5.Exercise1.{Empty, Stream}
import org.scalatest.{Matchers, WordSpec}

class Exercise8Spec extends WordSpec with Matchers {
  "Exercise 8" should {
    "implement constant" in {
      constant(1).take(0) shouldBe Empty
      constant(1).take(1).toList shouldBe Stream(1).toList
      constant(1).take(5).toList shouldBe Stream(1, 1, 1, 1, 1).toList
    }
  }

}
