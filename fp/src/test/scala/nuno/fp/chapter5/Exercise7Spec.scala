package nuno.fp.chapter5

import nuno.fp.chapter5.Exercise1.{Empty, Stream}
import org.scalatest.{Matchers, WordSpec}

class Exercise7Spec extends WordSpec with Matchers {
  "Exercise 7" should {
    "implement map" in {
      Stream().map(_.toString) should be(Empty)
      Stream(1).map(_.toString).toList should be(Stream("1").toList)
      Stream(1, 2).map(_.toString).toList should be(Stream("1", "2").toList)
    }

    "implement filter" in {
      Stream().filter(_ => false) should be(Empty)
      Stream().filter(_ => true) should be(Empty)
      Stream(1).filter(_ => false) should be(Empty)
      Stream(1).filter(_ => true).toList should be(Stream(1).toList)
      Stream(1, 2, 3, 4, 5, 6).filter(_ % 2 == 0).toList should be(Stream(2, 4, 6).toList)
    }

    "implement append" in {
      Stream().append(Stream()) should be(Empty)
      Stream(1).append(Stream()).toList should be(Stream(1).toList)
      Stream().append(Stream(1)).toList should be(Stream(1).toList)
      Stream(1).append(Stream(2)).toList should be(Stream(1, 2).toList)
    }

    "implement flatMap" in {
      Stream().flatMap(_ => Empty) should be(Empty)
      Stream().flatMap(_ => Stream(1)) should be(Empty)
      Stream(1).flatMap(_ => Empty) should be(Empty)
      Stream(1).flatMap(Stream(_)).toList should be(Stream(1).toList)
      Stream(1, 2, 3).flatMap(Stream(_)).toList should be(Stream(1, 2, 3).toList)
      Stream(1, 2, 3).flatMap(v => Stream(v, v)).toList should be(Stream(1, 1, 2, 2, 3, 3).toList)
    }
  }
}
