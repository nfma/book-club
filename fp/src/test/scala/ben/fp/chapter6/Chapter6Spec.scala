package ben.fp.chapter6

import ben.fp.chapter6.RNG.SimpleRNG
import org.scalatest.{WordSpec, Matchers, FreeSpec}


//class ExampleSpec extends PropSpec with PropertyChecks {
//
//  property("generate a random integer between 0 and Int.maxValue (inclusive)") {
//    forAll { (n: Int) =>
//      val tuple: (Int, RNG) = RNG.nonNegativeInt(SimpleRNG(n))
//      if(n < 0) {
//        tuple._1 == 0
//      }
//    }
//  }
//}


class Chapter6Spec extends WordSpec with Matchers {

  import RNG._

    "6.1 RNG nonNegativeInt" should {

      "generate a random integer between 0 and Int.maxValue (inclusive)" in {

        val (int, rng) = nonNegativeInt(SimpleRNG(Int.MinValue))
        int shouldBe 0
      }
    }


  "6.2 RNG double" should {

    "create a reproducable double" in {

      double(SimpleRNG(1)) shouldBe RNG.double(SimpleRNG(1))
      double(SimpleRNG(1))._1 shouldBe 1.7916224896907806E-4
    }
  }


  "6.3 RNG def intDouble, doubleInt, double3" should {

    "intDouble" in {

      intDouble(SimpleRNG(1)) match {
        case ((i:Int, d:Double), r:RNG) =>
        case _ => fail("no ints, doubles or RNGs?")
      }
    }

    "doubleInt" in {

      doubleInt(SimpleRNG(1)) match {
        case ((d:Double, i:Int), r:RNG) =>
        case _ => fail("no doubles, ints or RNGs?")
      }
    }

    "double3" in {

      double3(SimpleRNG(1)) match {
        case ((d:Double,d2:Double,d3:Double), r:RNG) =>
        case _ => fail("3 doubles and an RNG!")
      }
    }
  }
}
