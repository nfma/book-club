package nuno.fp.chapter5

import nuno.fp.chapter5.Exercise1.Stream
import org.scalatest.{Matchers, WordSpec}

class Exercise14Spec extends WordSpec with Matchers {
  "Exercise 14" should {
    "implement startsWith" in {
      Stream().startsWith(Stream()) shouldBe false
      Stream(1).startsWith(Stream()) shouldBe false
      Stream().startsWith(Stream(1)) shouldBe false
      Stream(1).startsWith(Stream(1)) shouldBe true
      Stream(1, 2).startsWith(Stream(1, 2)) shouldBe true
      Stream(1, 2, 3, 4).startsWith(Stream(1, 2)) shouldBe true
      Stream(1, 2, 3, 4).startsWith(Stream(1, 3)) shouldBe false
    }
  }
}
