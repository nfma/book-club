package misterflibble.fp.chapter2

import org.scalatest.{FunSpec, Matchers}
import misterflibble.fp.chapter2.Ex1._
import misterflibble.fp.chapter2.Ex2._
import misterflibble.fp.chapter2.Ex3._
import misterflibble.fp.chapter2.Ex4._
import misterflibble.fp.chapter2.Ex5._


class Ch02Spec extends FunSpec with Matchers {

  describe("Ex 1") {
    describe("fibonacci") {
      it("should produce the first few fibonacci numbers properly") {
        Range(0, 6).map(fib(_)) should equal(Seq(0, 1, 1, 2, 3, 5))
      }
    }
    describe("fibonacci of a big number") {
      fib(45) should equal(1134903170)
    }
  }

  describe("Ex 2") {
    describe("isSorted") {

      def gt(i: Int, j: Int): Boolean     = i > j
      def gtOrEq(i: Int, j: Int): Boolean = i >= j

      val a: Array[Int] = Array(1, 2, 3, 4, 5)
      val a2: Array[Int] = Array(1, 2, 3, 3, 4, 5)
      val b: Array[Int] = a.reverse
      val c: Array[Int] = Array(10, 2, 3, 4, 5)
      val c2: Array[Int] = Array(1, 2, 30, 4, 5)
      val c3: Array[Int] = Array(10, 2, 3, 40, 5)
      val emptyInts: Array[Int] = Array()
      val singletonInt: Array[Int] = Array(1)

      it("should return true on empty integer array  gt") {
        isSorted[Int](emptyInts, gt) should equal(true)
      }
      it("should return true on integer array of size 1 with gt") {
        isSorted[Int](singletonInt, gt) should equal(true)
      }
      it("should return true on ascending integer array with gt") {
        isSorted[Int](a, gt) should equal(true)
      }
      it("should return false on ascending integer array with repetitions, gt") {
        isSorted[Int](a2, gt) should equal(false)
      }
      it("should return true on ascending integer array with repetitions, gtOrEq") {
        isSorted[Int](a2, gtOrEq) should equal(true)
      }
      it("should return false on descending integer array with gt") {
        isSorted[Int](b, gt) should equal(false)
      }
      it("should return false on ascending integer array with first int too big, gt") {
        isSorted[Int](c, gt) should equal(false)
      }
      it("should return false on ascending integer array with middle int too big, gt") {
        isSorted[Int](c2, gt) should equal(false)
      }
      it("should return false on ascending integer array with first and middle int too big, gt") {
        isSorted[Int](c3, gt) should equal(false)
      }
    }
  }

  case class Ay(s:String)
  case class Bee(s:String)
  case class Cee(s:String)

  describe("Ex 3") {
    def ff(a:Ay, b:Bee) : Cee = Cee(a.s+"-"+b.s)
    it("normal fn to curried") {
        val k: Cee = curry(ff)(Ay("aay"))(Bee("bee"))
        k.toString should equal("Cee(aay-bee)")
        val expected = Cee("aay-bee")
        Cee(k.s) should equal(expected)

        k.getClass should equal(Cee("A").getClass)
        k should equal(Cee("aay-bee"))
      }
  }

  describe("Ex 4") {
    def eff(a:Ay) : (Bee=>Cee) =  (b:Bee) => Cee(a.s+"!"+(b.s))
    it("curried binary function to normal function") {
      val c: Cee = uncurry(eff)(Ay("aaaa"), Bee("bbbb"))
      c.toString should equal("Cee(aaaa!bbbb)")
      c should equal(Cee("aaaa!bbbb"))
    }
  }

  describe("Ex 5") {
    it("compose function"){
      def beeToCee(b:Bee): Cee = Cee("wasABee("+b.s+")")
      def ayyToBee(a:Ay): Bee = Bee("wasAnAy("+a.s+")")
      compose(beeToCee,ayyToBee)(Ay("text")) should equal(Cee("wasABee(wasAnAy(text))"))
    }
  }
}
