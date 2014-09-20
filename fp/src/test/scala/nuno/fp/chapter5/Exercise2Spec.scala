package nuno.fp.chapter5

import nuno.fp.chapter5.Exercise1.{Empty, Stream}
import org.scalatest.{Matchers, WordSpec}

class Exercise2Spec extends WordSpec with Matchers {
  "Exercise 2" should {
    "implement take" in {
      Stream() take 0 shouldBe Empty
      Stream() take -1 shouldBe Empty
      Stream() take 1 shouldBe Empty
      Stream(1).take(1).toList shouldBe Stream(1).toList
      Stream(1) take -1 shouldBe Empty
      Stream(1) take 0 shouldBe Empty
      Stream(1, 2).take(1).toList shouldBe Stream(1).toList
      Stream(1, 2).take(2).toList shouldBe Stream(1, 2).toList
      Stream(1, 2, 3, 4, 5).take(3).toList shouldBe Stream(1, 2, 3).toList
    }

    "implement drop" in {
      Stream() drop 0 shouldBe Empty
      Stream() drop -1 shouldBe Empty
      Stream() drop 1 shouldBe Empty
      Stream(1) drop 1 shouldBe Empty
      Stream(1).drop(-1).toList shouldBe Stream(1).toList
      Stream(1).drop(0).toList shouldBe Stream(1).toList
      Stream(1, 2).drop(1).toList shouldBe Stream(2).toList
      Stream(1, 2) drop 2 shouldBe Empty
      Stream(1, 2, 3, 4, 5).drop(3).toList shouldBe Stream(4, 5).toList
    }
  }
}
