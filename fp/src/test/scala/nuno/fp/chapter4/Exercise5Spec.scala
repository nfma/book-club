package nuno.fp.chapter4

import nuno.fp.chapter3.{List, Nil}
import nuno.fp.chapter4.Exercise1.{None, Some}
import nuno.fp.chapter4.Exercise5.{sequence, traverse}
import org.scalatest.{Matchers, WordSpec}

class Exercise5Spec extends WordSpec with Matchers {
  "Exercise 5" should {
    "implement traverse" in {
      traverse(Nil) {_ => None} shouldBe None
      traverse(Nil) {Some(_)} shouldBe None
      traverse(List(1)) {_ => None} shouldBe None
      traverse(List(1)) {Some(_)} shouldBe Some(List(1))
      traverse(List(1, 2, 3)) {Some(_)} shouldBe Some(List(1, 2, 3))
      traverse(List(1, 2, 3)) {
        case v if v % 2 == 0 => Some(v)
        case _ => None
      } shouldBe None
    }

    "implement sequence in terms of traverse" in {
      sequence(Nil) shouldBe None
      sequence(List(Some(1))) shouldBe Some(List(1))
      sequence(List(Some(1), None)) shouldBe None
      sequence(List(None, None)) shouldBe None
      sequence(List(Some(1), Some(2))) shouldBe Some(List(1, 2))
      sequence(List(Some(1), None, Some(2))) shouldBe None
    }
  }
}
