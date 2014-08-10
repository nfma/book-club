package nuno.fp.chapter2

import nuno.fp.chapter2.Exercise3._
import org.scalatest.{Matchers, WordSpec}

class Exercise3Spec extends WordSpec with Matchers {
  "Exercise 3" should {
    "curry the plus function" in {
      curry[Int, Int, Int](_ + _)(1)(1) should be(2)
    }

    // too many parenthesis around that concat function... clojure has less... :)
    "curry the concat function" in {
      curry[String, String, String](_.concat(_))("book")("-club") should be("book-club")
    }
  }
}
