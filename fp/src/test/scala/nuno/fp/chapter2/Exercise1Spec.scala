package nuno.fp.chapter2

import nuno.fp.chapter2.Exercise1.fib
import org.scalatest.{Matchers, WordSpec}

class Exercise1Spec extends WordSpec with Matchers {
  "Exercise 1" should {
    "calculate fibonnaci of 0" in {
      fib(0) shouldBe 0
    }

    "calculate fibonnaci of 1" in {
      fib(1) shouldBe 1
    }

    "calculate fibonnaci of 2" in {
      fib(2) shouldBe 1
    }

    "calculate fibonnaci of 3" in {
      fib(3) shouldBe 2
    }

    "calculate fibonnaci of 5" in {
      fib(5) shouldBe 5
    }

    "not blow up when calculating fibonnaci of 45" in {
      fib(45) shouldBe 1134903170
    }
  }
}
