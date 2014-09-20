package nuno.fp.chapter5

import nuno.fp.chapter5.Exercise1.{Empty, Stream}
import org.scalatest.{Matchers, WordSpec}

class Exercise5Spec extends WordSpec with Matchers {
  "Exercise 5" should {
    "implement takeWhile" in {
      Stream() takeWhile {_ => true} shouldBe Empty
      Stream() takeWhile {_ => false} shouldBe Empty
      Stream(1) takeWhile {_ => false} shouldBe Empty
      Stream(1).takeWhile {_ => true}.toList shouldBe Stream(1).toList
      Stream(1, 2, 3) takeWhile {_ => false} shouldBe Empty
      Stream(1, 2, 3).takeWhile {_ => true}.toList shouldBe Stream(1, 2, 3).toList
      Stream(0, 2, 4, 5, 6).takeWhile {_ % 2 == 0}.toList shouldBe Stream(0, 2, 4).toList
    }
  }
}
