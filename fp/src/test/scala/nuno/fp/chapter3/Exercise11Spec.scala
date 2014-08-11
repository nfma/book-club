package nuno.fp.chapter3

import nuno.fp.chapter3.Exercise11.{product, sum, length => len}
import org.scalatest.{Matchers, WordSpec}

class Exercise11Spec extends WordSpec with Matchers {
  "Exercise 11" should {
    "return 0 as the sum of an empty list" in {
      sum(Nil) should be(0)
    }

    "return the element as the sum of a single element list" in {
      sum(List(1)) should be(1)
    }

    "return the sum of the elements as the sum of a two element list" in {
      sum(List(1, 1)) should be(2)
    }

    "return the sum of the elements as the sum of a three element list" in {
      sum(List(1, 1, 1)) should be(3)
    }

    "return 1 as the product of an empty list" in {
      product(Nil) should be(1)
    }

    "return the element as the product of a single element list" in {
      product(List(2)) should be(2)
    }

    "return the product of the two elements as the product of a two element list" in {
      product(List(2, 2)) should be(4)
    }

    "return the product of the elements as the product of a three element list" in {
      product(List(2, 2, 2)) should be(8)
    }

    "return 0 as the length of an empty list" in {
      len(Nil) should be(0)
    }

    "return 1 as the length of a single element list" in {
      len(List(1)) should be(1)
    }

    "return 2 as the length of a two element list" in {
      len(List(2, 2)) should be(2)
    }

    "return 3 as the length of a three element list" in {
      len(List(2, 2, 2)) should be(3)
    }
  }
}
