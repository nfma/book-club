package nuno.fp.chapter3

import nuno.fp.chapter3.Exercise5.dropWhile
import org.scalatest.{Matchers, WordSpec}

class Exercise5Spec extends WordSpec with Matchers {
  "Exercise 5" should {
    "return the original list when dropping with false" in {
      dropWhile[Int](List(1), _ => false) shouldBe List(1)
      dropWhile[Int](Nil, _ => false) shouldBe Nil
    }

    "return an empty list when dropping with true" in {
      dropWhile[Int](Nil, _ => true) shouldBe Nil
      dropWhile[Int](List(1), _ => true) shouldBe Nil
      dropWhile[Int](List(1, 2, 3, 4), _ => true) shouldBe Nil
    }

    "return a list with X less elements when dropping returns false after X elements" in {
      dropWhile[Int](List(2, 4, 5), _ % 2 == 0) shouldBe List(5)
      dropWhile[Int](List(1, 3, 5, 2, 4), _ % 2 == 1) shouldBe List(2, 4)
    }
  }

}
