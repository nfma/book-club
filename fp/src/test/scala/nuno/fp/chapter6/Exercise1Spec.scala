package nuno.fp.chapter6

import nuno.fp.chapter6.Exercise1.{Simple, nonNegativeInt}
import org.scalatest.{Matchers, WordSpec}

class Exercise1Spec extends WordSpec with Matchers {
  "Exercise 1" should {
    "implement nonNegativeInt" in {
      nonNegativeInt(Simple(42)) should be(16159453, Simple(1059025964525L))
      nonNegativeInt(Simple(1059025964525L)) should be(1281479697, Simple(197491923327988L))
      nonNegativeInt(Simple(1059025964525L)) should be(1281479697, Simple(197491923327988L))
      nonNegativeInt(Simple(197491923327988L)) should be(340305902, Simple(259172689157871L))
    }
  }
}
