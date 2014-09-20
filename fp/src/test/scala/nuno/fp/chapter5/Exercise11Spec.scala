package nuno.fp.chapter5

import nuno.fp.chapter4.Exercise1.{None, Some}
import nuno.fp.chapter5.Exercise1.Stream.unfold
import nuno.fp.chapter5.Exercise1.{Empty, Stream}
import org.scalatest.{Matchers, WordSpec}

class Exercise11Spec extends WordSpec with Matchers {
  "Exercise 11" should {
    "implement unfold" in {
      unfold(0) {_ => None} shouldBe Empty
      unfold(0) {
        case n if n < 3 => Some((1, n + 1))
        case _ => None
      }.toList shouldBe Stream(1, 1, 1).toList
      unfold(0) {n => Some((n * n, n + 1))}.take(4).toList shouldBe Stream(0, 1, 4, 9).toList
    }
  }
}
