package nuno.fp.chapter3

import nuno.fp.chapter3.Exercise16.addOne
import nuno.fp.chapter3.Exercise17.doubleToString
import nuno.fp.chapter3.Exercise18.map
import org.scalatest.{Matchers, WordSpec}

class Exercise18Spec extends WordSpec with Matchers {
  "Exercise 18" should {
    "return an empty list when mapping over an empty list" in {
      map(Nil) {identity} shouldBe Nil
    }

    "return the element wrapped in a list when mapping over a single element list with identity" in {
      map(List(1)) {identity} shouldBe List(1)
    }

    "return the elements incremented by one in a list when mapping over an multi element list with increment" in {
      map(List(1, 2, 3)) {_ + 1} shouldBe addOne(List(1, 2, 3))
    }

    "return the elements in a string in a list when mapping over an multi element list with double to string" in {
      map(List(1.0, 2.0, 3.0)) {_.toString} shouldBe doubleToString(List(1.0, 2.0, 3.0))
    }
  }
}
