package misterflibble.fp.chapter2

import misterflibble.fp.chapter2.Ex1._
import org.scalatest.{Matchers, FunSpec}

class Ch03Spec extends FunSpec with Matchers {
  import misterflibble.fp.chapter3.Ex1._
  describe("Ex 1") {
    describe("fibonacci") {
      it("should just evaluate") {
        ex1 should equal(101)
      }
    }
  }

  describe("Ex 2") {
    import misterflibble.fp.chapter3.Ex2._

    it("should do stuff") {
      tail(Cons(1,Cons(2,Cons(3,Nil)))) should equal(Cons(2,Cons(3,Nil)))
      tail(Nil) should equal(Nil)
    }
  }

  describe("Ex 3") {
    import misterflibble.fp.chapter3.Ex3._
    it("should do stuff") {
    // setHead
    }
  }


}
