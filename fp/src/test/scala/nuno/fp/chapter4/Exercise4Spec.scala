package nuno.fp.chapter4

import nuno.fp.chapter3.{List, Nil}
import nuno.fp.chapter4.Exercise1.{Some, None}
import nuno.fp.chapter4.Exercise4.sequence
import org.scalatest.{Matchers, WordSpec}

class Exercise4Spec extends WordSpec with Matchers {
  "Exercise 4" should {
    "implement sequence" in {
      sequence(Nil) should be(None)
      sequence(List(Some(1))) should be(Some(List(1)))
      sequence(List(Some(1), None)) should be(None)
      sequence(List(None, None)) should be(None)
      sequence(List(Some(1), Some(2))) should be(Some(List(1, 2)))
      sequence(List(Some(1), None, Some(2))) should be(None)
    }
  }
}
