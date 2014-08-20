package nuno.fp.chapter5

import nuno.fp.chapter5.Exercise1.{Empty, Stream}
import org.scalatest.{Matchers, WordSpec}

class Exercise2Spec extends WordSpec with Matchers {
  "Exercise 2" should {
    "implement take" in {
      Stream().take(0) should be(Empty)
      Stream().take(-1) should be(Empty)
      Stream().take(1) should be(Empty)
      Stream(1).take(1).toList should be(Stream(1).toList)
      Stream(1).take(-1) should be(Empty)
      Stream(1).take(0) should be(Empty)
      Stream(1, 2).take(1).toList should be(Stream(1).toList)
      Stream(1, 2).take(2).toList should be(Stream(1, 2).toList)
      Stream(1, 2, 3, 4, 5).take(3).toList should be(Stream(1, 2, 3).toList)
    }

    "implement drop" in {
      Stream().drop(0) should be(Empty)
      Stream().drop(-1) should be(Empty)
      Stream().drop(1) should be(Empty)
      Stream(1).drop(1) should be(Empty)
      Stream(1).drop(-1).toList should be(Stream(1).toList)
      Stream(1).drop(0).toList should be(Stream(1).toList)
      Stream(1, 2).drop(1).toList should be(Stream(2).toList)
      Stream(1, 2).drop(2) should be(Empty)
      Stream(1, 2, 3, 4, 5).drop(3).toList should be(Stream(4, 5).toList)
    }
  }
}
