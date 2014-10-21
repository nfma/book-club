package paul.fp.chapter6

import paul.fp.chapter6.Exercise1.{Simple, nonNegativeInt}
import paul.fp.chapter6.Exercise8.flatMap
import org.scalatest.{Matchers, WordSpec}

class Exercise8Spec extends WordSpec with Matchers {
  "Exercise 8" should {
    "implement fmap" in {
      flatMap(nonNegativeInt) {_ => nonNegativeInt} {Simple(42)} shouldBe (1281479697, Simple(197491923327988L))
      flatMap(nonNegativeInt) {a => rng => {
        val (b, rng2) = rng.nextInt
        (b + a, rng2)
      }} {Simple(42)} shouldBe (-1265320244, Simple(197491923327988L))
    }
  }
}
