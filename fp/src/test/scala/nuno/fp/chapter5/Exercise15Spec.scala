package nuno.fp.chapter5

import nuno.fp.chapter5.Exercise1.{Empty, Stream}
import org.scalatest.{Matchers, WordSpec}

class Exercise15Spec extends WordSpec with Matchers {
  "Exercise 15" should {
    "implement tails" in {
      Stream().tails shouldBe Empty
      Stream(1).tails.map {_.toList}.toList shouldBe Stream(Stream(1).toList).toList
      Stream(1, 2).tails.map {_.toList}.toList shouldBe Stream(Stream(1, 2).toList, Stream(2).toList).toList
      Stream(1, 2, 3).tails.map {_.toList}.toList shouldBe Stream(Stream(1, 2, 3).toList, Stream(2, 3).toList, Stream(3).toList).toList
    }
  }
}
