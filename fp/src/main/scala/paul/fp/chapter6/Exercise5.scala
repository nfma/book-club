package paul.fp.chapter6

import paul.fp.chapter6.Exercise1._

object Exercise5 {

  def doubleOld(rng: RNG): (Double, RNG) = nonNegativeInt(rng) match {
    case (i, rng) => (i.toDouble / Int.MaxValue , rng)
  }

  def double(rng: RNG): Rand[Double] = map(nonNegativeInt)(_.toDouble / Int.MaxValue)
}
