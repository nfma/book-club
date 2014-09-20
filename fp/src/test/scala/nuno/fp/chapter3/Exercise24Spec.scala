package nuno.fp.chapter3

import nuno.fp.chapter3.Exercise24.hasSubsequence
import org.scalatest.{Matchers, WordSpec}

class Exercise24Spec extends WordSpec with Matchers {
  "Exercise 24" should {
    "return false when searching on an empty list" in {
      hasSubsequence(Nil, List(1)) shouldBe false
    }

    "return false when searching for an empty list" in {
      hasSubsequence(Nil, Nil) shouldBe false
      hasSubsequence(List(1), Nil) shouldBe false
    }

    "be able to search for an element in a list" in {
      hasSubsequence(List(1), List(1)) shouldBe true
      hasSubsequence(List(1, 2), List(1)) shouldBe true
      hasSubsequence(List(1, 2, 3), List(3)) shouldBe true
      hasSubsequence(List(1, 2, 3), List(4)) shouldBe false
    }

    "be able to search for two elements in a list" in {
      hasSubsequence(List(1), List(1, 2)) shouldBe false
      hasSubsequence(List(1, 2), List(1, 2)) shouldBe true
      hasSubsequence(List(1, 2, 3), List(2, 3)) shouldBe true
      hasSubsequence(List(1, 2, 3), List(3, 4)) shouldBe false
    }

    "be able to search for three elements in a list" in {
      hasSubsequence(List(1), List(1, 2, 3)) shouldBe false
      hasSubsequence(List(1, 2), List(1, 2, 3)) shouldBe false
      hasSubsequence(List(1, 2, 3), List(1, 2, 3)) shouldBe true
      hasSubsequence(List(1, 2, 3, 4), List(1, 2, 3)) shouldBe true
      hasSubsequence(List(0, 1, 2, 3), List(1, 2, 3)) shouldBe true
      hasSubsequence(List(0, 1, 2, 3, 4), List(1, 2, 3)) shouldBe true
      hasSubsequence(List(1, 2, 3), List(2, 3, 4)) shouldBe false
    }
  }
}
