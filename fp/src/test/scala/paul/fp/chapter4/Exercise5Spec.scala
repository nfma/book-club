package paul.fp.chapter4

import paul.fp.chapter4.Exercise5.{traverse}
import org.scalatest.{Matchers, WordSpec}

class Exercise5Spec extends WordSpec with Matchers {
  "Exercise 5" should {
    "implement traverse" in {
      traverse(Nil)(_ => None) should be(Some(Nil))
      traverse(Nil)(v => Some(v)) should be(Some(Nil))
      traverse(List(1))(_ => None) should be(None)
      traverse(List(1,2,3))(v => Some(v)) should be(Some(List(1,2,3)))
      traverse(List(1, 2, 3))(v => Some(v)) should be(Some(List(1, 2, 3)))
    }
  }
}
