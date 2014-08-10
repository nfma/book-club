package nuno.fp.chapter3

import Exercise9.{length => ln}
import org.scalatest.{Matchers, WordSpec}

class Exercise9Spec extends WordSpec with Matchers {
  "Exercise 9" should {
    "return 0 as the length of an empty list" in {
      ln(Nil) should be(0)
    }

    "return 1 as the length of a single element list" in {
      ln(List(1)) should be(1)
    }

    "return 2 as the length of a double element list" in {
      ln(List(1, 2)) should be(2)
    }

    "return 5 as the length of a five element list" in {
      ln(List(1, 2, 3, 4, 5)) should be(5)
    }
  }
}
