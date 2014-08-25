package nuno.fp.chapter6

import nuno.fp.chapter6.Exercise1.{Simple, nonNegativeInt}
import nuno.fp.chapter6.Exercise6.map2
import org.scalatest.{Matchers, WordSpec}

class Exercise6Spec extends WordSpec with Matchers {
  "Exercise 6" should {
    "implement map2" in {
      map2(nonNegativeInt, nonNegativeInt)((_, _))(Simple(42)) should be((16159453, 1281479697), Simple(197491923327988L))
    }
  }
}
