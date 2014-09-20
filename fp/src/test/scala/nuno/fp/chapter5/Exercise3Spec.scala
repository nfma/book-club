package nuno.fp.chapter5

import nuno.fp.chapter5.Exercise1.{Empty, Stream}
import org.scalatest.{Matchers, WordSpec}

class Exercise3Spec extends WordSpec with Matchers {
  "Exercise 3" should {
    "implement takeWhile" in {
      Stream() dropWhile {_ => true} shouldBe Empty
      Stream() dropWhile {_ => false} shouldBe Empty
      Stream(1).dropWhile {_ => false}.toList shouldBe Stream(1).toList
      Stream(1) dropWhile {_ => true} shouldBe Empty
      Stream(1, 2).dropWhile {_ => false}.toList shouldBe Stream(1, 2).toList
      Stream(1, 2) dropWhile {_ => true} shouldBe Empty
      Stream(0, 2, 4, 5, 6).dropWhile {_ % 2 == 0}.toList shouldBe Stream(5, 6).toList
    }
  }
}
