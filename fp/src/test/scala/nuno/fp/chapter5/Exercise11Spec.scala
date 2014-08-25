package nuno.fp.chapter5

import nuno.fp.chapter4.Exercise1.{None, Some}
import nuno.fp.chapter5.Exercise1.{Empty, Stream}
import nuno.fp.chapter5.Exercise1.Stream.unfold
import org.scalatest.{Matchers, WordSpec}

class Exercise11Spec extends WordSpec with Matchers {
  "Exercise 11" should {
    "implement unfold" in {
      unfold(0)(n => None) should be(Empty)
      unfold(0)(n => if (n < 3) Some((1, n + 1)) else None).toList should be(Stream(1, 1, 1).toList)
      unfold(0)(n => Some((n * n, n + 1))).take(4).toList should be(Stream(0, 1, 4, 9).toList)
    }
  }
}
