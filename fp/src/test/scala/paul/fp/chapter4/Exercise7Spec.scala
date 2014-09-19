package paul.fp.chapter4

import paul.fp.chapter4.Exercise6.Right
import paul.fp.chapter4.Exercise7.sequence
import org.scalatest.{Matchers, WordSpec}

class Exercise7Spec extends WordSpec with Matchers {
  "Exercise 7" should {
    "implement sequence" in {
      sequence(Nil) should be(Right(Nil))
      sequence(List(Right(1))) should be(Right(List(1)))
      sequence(List(Right(1), Right(2))) should be(Right(List(1, 2)))
    }
  }
}
