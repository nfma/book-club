package nuno.fp.chapter5

import nuno.fp.chapter5.Exercise1.Empty
import nuno.fp.chapter5.Exercise1.Stream.from
import org.scalatest.{Matchers, WordSpec}

class Exercise9Spec extends WordSpec with Matchers {
  "Exercise 9" should {
    "implement from" in {
      from(0).take(0) shouldBe Empty
      from(0).take(1).toList shouldBe Stream(0).toList
      from(0).take(2).toList shouldBe Stream(0, 1).toList
      from(0).take(5).toList shouldBe Stream(0, 1, 2, 3, 4).toList
    }
  }
}
