package paul.fp.chapter6

import paul.fp.chapter6.Exercise1.Simple
import paul.fp.chapter6.Exercise3.{double3, doubleInt, intDouble}
import org.scalatest.{Matchers, WordSpec}

class Exercise3Spec extends WordSpec with Matchers {
  "Exercise 3" should {
    "implement intDouble" in {
      intDouble {Simple(42)} shouldBe ((16159453, 0.5967354856416283), Simple(197491923327988L))
    }

    "implement doubleInt" in {
      doubleInt {Simple(42)} shouldBe ((0.5967354856416283, 16159453), Simple(197491923327988L))
    }

    "implement double3" in {
      double3 {Simple(42)} shouldBe ((0.007524831689672932, 0.5967354856416283, 0.15846728447753344), Simple(259172689157871L))
    }
  }
}
