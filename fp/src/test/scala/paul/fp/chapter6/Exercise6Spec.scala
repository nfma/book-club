package paul.fp.chapter6

import org.scalatest.{Matchers, WordSpec}
import paul.fp.chapter6.Exercise1._
import paul.fp.chapter6.Exercise6.map2

class Exercise6Spec extends WordSpec with Matchers {
  "Exercise 6" should {
    "implement map2" in {
      map2(nonNegativeInt, nonNegativeInt) ((_, _)) (Simple(42)) shouldBe ((16159453, 1281479697), Simple(197491923327988L))
    }
  }
}
