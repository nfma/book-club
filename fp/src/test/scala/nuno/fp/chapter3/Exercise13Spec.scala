package nuno.fp.chapter3

import nuno.fp.chapter3.Exercise13.{foldLeft, foldRight}
import org.scalatest.{Matchers, WordSpec}

class Exercise13Spec extends WordSpec with Matchers {
  def concat(a: String, b: String) = a + b

  "Exercise 13" should {
    "fold left with fold right" in {
      foldLeft(Nil, "a") {concat} shouldBe "a"
      foldLeft(List("b"), "a") {concat} shouldBe "ab"
      foldLeft(List("b", "c"), "a") {concat} shouldBe "abc"
    }

    "fold right with fold left" in {
      foldRight(Nil, "a") {concat} shouldBe "a"
      foldRight(List("a"), "b") {concat} shouldBe "ab"
      foldRight(List("a", "b"), "c") {concat} shouldBe "abc"
    }
  }
}
