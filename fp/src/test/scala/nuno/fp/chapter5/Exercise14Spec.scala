package nuno.fp.chapter5

import nuno.fp.chapter5.Exercise1.Stream
import org.scalatest.{Matchers, WordSpec}

class Exercise14Spec extends WordSpec with Matchers {
  "Exercise 14" should {
    "implement startsWith" in {
      Stream().startsWith(Stream()) should be(false)
      Stream(1).startsWith(Stream()) should be(false)
      Stream().startsWith(Stream(1)) should be(false)
      Stream(1).startsWith(Stream(1)) should be(true)
      Stream(1, 2).startsWith(Stream(1, 2)) should be(true)
      Stream(1, 2, 3, 4).startsWith(Stream(1, 2)) should be(true)
      Stream(1, 2, 3, 4).startsWith(Stream(1, 3)) should be(false)
    }
  }
}
