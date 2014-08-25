package nuno.fp.chapter5

import nuno.fp.chapter5.Exercise1.Empty
import nuno.fp.chapter5.Exercise10.fibs
import org.scalatest.{Matchers, WordSpec}

class Exercise10Spec extends WordSpec with Matchers {
  "Exercise 10" should {
    "implement fibonacci sequence" in {
      fibs.take(0) should be(Empty)
      fibs.take(1).toList should be(Stream(0).toList)
      fibs.take(2).toList should be(Stream(0, 1).toList)
      fibs.take(3).toList should be(Stream(0, 1, 1).toList)
      fibs.take(4).toList should be(Stream(0, 1, 1, 2).toList)
      fibs.take(5).toList should be(Stream(0, 1, 1, 2, 3).toList)
      fibs.take(6).toList should be(Stream(0, 1, 1, 2, 3, 5).toList)
      fibs.take(7).toList should be(Stream(0, 1, 1, 2, 3, 5, 8).toList)
    }
  }
}
