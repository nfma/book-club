package fp.chapter2

import org.scalatest.{FunSpec, Matchers}

class Chapter2Spec extends FunSpec with Matchers {

  describe("Exercise 1") {
    describe("fib") {
      it("should produce 1 when input is 2") {
        Exercise1.fib(2) should equal(1)
      }
    }
  }

  describe("Exercise 2") {
    describe("isSorted - should determine if an array is sorted") {
      it("should return false when an array is not sorted") {

      }
    }
  }
}
