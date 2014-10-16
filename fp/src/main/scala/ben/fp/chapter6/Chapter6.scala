package ben.fp.chapter6

trait RNG {
  def nextInt: (Int, RNG)
}

object RNG {

  case class SimpleRNG(seed: Long) extends RNG {
    def nextInt: (Int, RNG) = {
      val newSeed = (seed * 0x5DEECE66DL + 0xBL) & 0xFFFFFFFFFFFFL
      val nextRNG = SimpleRNG(newSeed)
      val n = (newSeed >>> 16).toInt
      (n, nextRNG)
    }
  }

  def nonNegativeInt(rng: RNG): (Int, RNG) = {
    rng.nextInt match {
      case (int, nextRng) if int < 0 => (0, nextRng)
      case result => result
    }
  }

  def double(rng: RNG): (Double, RNG) = {
    val (i, r) = nonNegativeInt(rng)
    (i / (Int.MaxValue.toDouble + 1), r)
  }

  def intDouble(rng: RNG): ((Int,Double), RNG) = {
    val (int, gen ) = rng.nextInt
    val (dub, gen2) = double(rng)
    (int -> dub) -> gen2
  }
  
  def doubleInt(rng: RNG): ((Double,Int), RNG) = {
    val ((i , d), g) = intDouble(rng)
    (d -> i ) -> g
  }

  def double3(rng: RNG): ((Double,Double,Double), RNG) = {
    val (dub1, gen1) = double(rng)
    val (dub2, gen2) = double(gen1)
    val (dub3, gen3) = double(gen2)
    (dub1, dub2, dub3) -> gen3
  }
}
