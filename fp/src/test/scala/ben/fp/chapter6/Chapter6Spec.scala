package ben.fp.chapter6


import org.scalatest.{Matchers, WordSpec}


class Chapter6Spec extends WordSpec with Matchers {

  import ben.fp.chapter6.RNG._

  val seed: SimpleRNG = SimpleRNG(1)

  "6.1 RNG nonNegativeInt" should {

    "generate a random integer between 0 and Int.maxValue (inclusive)" in {

      val (int, rng) = nonNegativeInt(SimpleRNG(Int.MinValue))
      int shouldBe 1932951552
    }
  }

  "6.2 RNG" should {

    "create a reproducable double" in {

      double(seed) shouldBe RNG.double(seed)
      double(seed)._1 shouldBe 1.7916224896907806E-4
    }
  }


  "6.3 RNG" should {

    "intDouble" in {

      intDouble(seed) match {
        case ((i: Int, d: Double), r: RNG) =>
        case _ => fail("no ints, doubles or RNGs?")
      }
    }

    "doubleInt" in {

      doubleInt(seed) match {
        case ((d: Double, i: Int), r: RNG) =>
        case _ => fail("no doubles, ints or RNGs?")
      }
    }

    "double3" in {

      double3(seed) match {
        case ((d: Double, d2: Double, d3: Double), r: RNG) =>
        case _ => fail("3 doubles and an RNG!")
      }
    }
  }


  "6.4 RNG" should {

    "generate a list of random integers" in {

      val inty: (List[Int], RNG) = ints(10)(seed)
      inty._1.length shouldBe 10

      val inty2: (List[Int], RNG) = ints(0)(seed)
      inty2._1 shouldBe Nil
    }
  }

  "6.5 RNG" should {

    "create a reproducable double" in {

      double2 {SimpleRNG(42)} shouldBe (0.007524831689672932, SimpleRNG(1059025964525L))
      double2 {SimpleRNG(1059025964525L)} shouldBe (0.5967354856416283, SimpleRNG(197491923327988L))
      double2 {SimpleRNG(1059025964525L)} shouldBe (0.5967354856416283, SimpleRNG(197491923327988L))
      double2 {SimpleRNG(197491923327988L)} shouldBe (0.15846728447753344, SimpleRNG(259172689157871L))
      double2 {SimpleRNG(259172689157871L)} shouldBe (0.9386595436086224, SimpleRNG(149370390209998L))
    }
  }



  "6.6 RNG" should {

    "create a reproducable double" in {

      map2(nonNegativeInt, nonNegativeInt)(_ + _)(SimpleRNG(42)) shouldBe (1297639150,SimpleRNG(197491923327988L))
    }
  }
}
