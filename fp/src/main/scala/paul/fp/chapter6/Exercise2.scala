package paul.fp.chapter6

import paul.fp.chapter6.Exercise1.{RNG, nonNegativeInt}

object Exercise2 {
  def double(rng: RNG): (Double, RNG) = nonNegativeInt(rng) match {
    case (i, rng) => (i.toDouble / Int.MaxValue , rng)
  }
}
