package nuno.fp.chapter4

import nuno.fp.chapter3.{List, Nil}
import nuno.fp.chapter4.Exercise1.{None, Some}
import nuno.fp.chapter4.Exercise5.{traverse, sequence}
import org.scalatest.{Matchers, WordSpec}

class Exercise5Spec extends WordSpec with Matchers {
  "Exercise 5" should {
    "implement traverse" in {
      traverse(Nil)(_ => None) should be(None)
      traverse(Nil)(v => Some(v)) should be(None)
      traverse(List(1))(_ => None) should be(None)
      traverse(List(1))(v => Some(v)) should be(Some(List(1)))
      traverse(List(1, 2, 3))(v => Some(v)) should be(Some(List(1, 2, 3)))
      traverse(List(1, 2, 3))(v => if (v % 2 == 0) Some(v) else None) should be(None)
    }

    "implement sequence in terms of traverse" in {
      sequence(Nil) should be(None)
      sequence(List(Some(1))) should be(Some(List(1)))
      sequence(List(Some(1), None)) should be(None)
      sequence(List(None, None)) should be(None)
      sequence(List(Some(1), Some(2))) should be(Some(List(1, 2)))
      sequence(List(Some(1), None, Some(2))) should be(None)
    }
  }
}
