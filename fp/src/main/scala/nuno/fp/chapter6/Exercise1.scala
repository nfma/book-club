package nuno.fp.chapter6

object Exercise1 {

  def nonNegativeInt(rng: RNG): (Int, RNG) = rng.nextInt match {
    case (i, gen) if i == Int.MinValue => (0, gen)
    case (i, gen) => (Math.abs(i), gen)
  }

  trait RNG {
    def nextInt: (Int, RNG)
  }

  case class Simple(seed: Long) extends RNG {
    def nextInt: (Int, RNG) = {
      val newSeed = (seed * 0x5DEECE66DL + 0xBL) & 0xFFFFFFFFFFFFL
      val nextRNG = Simple(newSeed)
      val n = (newSeed >>> 16).toInt
      (n, nextRNG)
    }
  }

}
