package nuno.fp.chapter2

import nuno.fp.chapter2.Exercise3.curry
import nuno.fp.chapter2.Exercise4.uncurry
import org.scalatest.{Matchers, WordSpec}

class Exercise4Spec extends WordSpec with Matchers {
  "Exercise 4" should {
    "uncurry a curried addition function" in {
      uncurry(curry[Int, Int, Int](_ + _))(1, 1) should be(2)
    }

    "uncurry a curried concat function" in {
      uncurry(curry[String, String, String](_.concat(_)))("book-", "club") should be("book-club")
    }
  }
}
