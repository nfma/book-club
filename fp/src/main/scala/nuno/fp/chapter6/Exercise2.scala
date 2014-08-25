package nuno.fp.chapter6

import nuno.fp.chapter6.Exercise1.{RNG, nonNegativeInt}

object Exercise2 {
  def double(rng: RNG): (Double, RNG) = nonNegativeInt(rng) match {
    case (i, r) => (i.toDouble / Int.MaxValue.toDouble, r)
  }
}
