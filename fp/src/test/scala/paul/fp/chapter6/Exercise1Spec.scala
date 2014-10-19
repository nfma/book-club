package paul.fp.chapter6

import paul.fp.chapter6.Exercise1.{Simple, nonNegativeInt}
import org.scalatest.{Matchers, WordSpec}

class Exercise1Spec extends WordSpec with Matchers {
  "Exercise 1" should {
    "implement nonNegativeInt" in {
      nonNegativeInt {Simple(42)} shouldBe (16159453, Simple(1059025964525L))
      nonNegativeInt {Simple(1059025964525L)} shouldBe (1281479697, Simple(197491923327988L))
      nonNegativeInt {Simple(1059025964525L)} shouldBe (1281479697, Simple(197491923327988L))
      nonNegativeInt {Simple(197491923327988L)} shouldBe (340305902, Simple(259172689157871L))
    }
  }
}
