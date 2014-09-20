package nuno.fp.chapter4

import nuno.fp.chapter3.{List, Nil}
import nuno.fp.chapter4.Exercise1.{None, Some}
import nuno.fp.chapter4.Exercise4.sequence
import org.scalatest.{Matchers, WordSpec}

class Exercise4Spec extends WordSpec with Matchers {
  "Exercise 4" should {
    "implement sequence" in {
      sequence(Nil) shouldBe None
      sequence(List(Some(1))) shouldBe Some(List(1))
      sequence(List(Some(1), None)) shouldBe None
      sequence(List(None, None)) shouldBe None
      sequence(List(Some(1), Some(2))) shouldBe Some(List(1, 2))
      sequence(List(Some(1), None, Some(2))) shouldBe None
    }
  }
}
