package nuno.fp.chapter3

import nuno.fp.chapter3.Exercise22.zipSum
import org.scalatest.{Matchers, WordSpec}

class Exercise22Spec extends WordSpec with Matchers {
  "Exercise 22" should {
    "return an empty list when zipSumming two empty lists" in {
      zipSum(Nil, Nil) should be(Nil)
    }

    "return a single element list with the sum of two elements when zipSumming two lists of one element" in {
      zipSum(List(1), List(1)) should be(List(2))
    }

    "return a single element list with the sum of two elements when zipSumming two lists one of one element and another of two" in {
      zipSum(List(1, 2), List(1)) should be(List(2))
      zipSum(List(1), List(1, 2)) should be(List(2))
    }

    "return a double element list with the sum of two and two elements when zipSumming two lists of two elements" in {
      zipSum(List(1, 2), List(1, 2)) should be(List(2, 4))
    }

    "conform to the example given in the book" in {
      zipSum(List(1, 2, 3), List(4, 5, 6)) should be(List(5, 7, 9))
    }
  }
}
