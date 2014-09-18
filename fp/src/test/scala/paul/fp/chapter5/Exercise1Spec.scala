package paul.fp.chapter5

import paul.fp.chapter5.Exercise1.Stream
import org.scalatest.{Matchers, WordSpec}

class Exercise1Spec extends WordSpec with Matchers {
  "Exercise 1" should {
    "implement toList" in {
      Stream().toList should be(Nil)
      Stream(1).toList should be(List(1))
      Stream(1, 2).toList should be(List(1, 2))
      Stream(1, 2, 3, 4, 5).toList should be(List(1, 2, 3, 4, 5))
    }
  }
}
