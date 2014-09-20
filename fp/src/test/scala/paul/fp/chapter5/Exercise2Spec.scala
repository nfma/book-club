package paul.fp.chapter5

import org.scalatest.{Matchers, WordSpec}
import paul.fp.chapter5.Exercise1.Stream

class Exercise2Spec extends WordSpec with Matchers {
  "Exercise 2" should {
    "implement take(n)" in {
      Stream(1, 2, 3, 4, 5).toList should be(List(1, 2, 3, 4, 5))
    }
  }
}
