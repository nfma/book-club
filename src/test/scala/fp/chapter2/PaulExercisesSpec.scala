package fp.chapter2

import fp.chapter2.PaulSolutionExercise1._
import fp.chapter2.PaulSolutionExercise3._
import fp.chapter2.PaulSolutionExercise4._
import fp.chapter2.PaulSolutionExercise5._
import org.scalatest.{FunSpec, Matchers}

class PaulExercisesSpec extends FunSpec with Matchers {

  describe("Exercise 1") {
    describe("fib") {
      it("should produce 1 when input is 2") {
        fib(2) should equal(1)
      }
    }
  }

  describe("Exercise 2") {
    describe("isSorted - should determine if an array is sorted") {
      it("should return false when an array is not sorted") {

      }
    }
  }

  describe("Exercise 3") {
    describe("curry") {
      it("should converts a function f of two arguments into a function of one argument that partially applies f") {
        val addition = (a:Int,b:Int) => a+b
        println(curry(addition),(10),(4),(3))
      }
    }
  }

  describe("Exercise 4") {
    describe("uncurry") {
      it("should reverse the transformation of curry") {
        val addition = (a:Int,b:Int) => a+b
        uncurry(curry(addition))(10,2) should be (13)
      }
    }
  }

  describe("Exercise 5") {
    describe("compose") {
      it("should compose two functions") {
        val addition = (a:Int) => a+1
        val multiplication = (a:Int) => a*3
        compose(addition,multiplication)(10) should be (31)
      }
    }
  }
}
