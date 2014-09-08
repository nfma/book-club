package nuno.fp.chapter6

import nuno.fp.chapter6.Exercise1.{Simple, nonNegativeInt}
import nuno.fp.chapter6.Exercise9.{map, map2}
import org.scalatest.{Matchers, WordSpec}

class Exercise9Spec extends WordSpec with Matchers {
  "Exercise 9" should {
    "implement map in terms of flatMap" in {
      map(nonNegativeInt)(_ * 2)(Simple(42)) should be(32318906, Simple(1059025964525L))
    }

    "implement map2 in terms of flatMap" in {
      map2(nonNegativeInt, nonNegativeInt)((_, _))(Simple(42)) should be((16159453, 1281479697), Simple(197491923327988L))
    }
  }
}
