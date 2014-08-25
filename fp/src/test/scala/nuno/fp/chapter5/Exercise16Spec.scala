package nuno.fp.chapter5

import nuno.fp.chapter5.Exercise1.Stream
import org.scalatest.{Matchers, WordSpec}

class Exercise16Spec extends WordSpec with Matchers {
  "Exercise 16" should {
    "answer question" in {
      println("I don't think it can be done with unfold because it folds left instead of right and even though you can transform one into another for strict applications, it fails in lazy ones")
    }

    "implement scanRight" in {
      Stream[Int]().scanRight(0)(_ + _).toList should be(List(0))
      Stream[Int](1).scanRight(0)(_ + _).toList should be(List(1, 0))
      Stream[Int](1, 2).scanRight(0)(_ + _).toList should be(List(3, 2, 0))
      Stream[Int](1, 2, 3).scanRight(0)(_ + _).toList should be(List(6, 5, 3, 0))
    }
  }
}
