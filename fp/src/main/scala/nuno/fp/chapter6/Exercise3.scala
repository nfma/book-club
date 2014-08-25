package nuno.fp.chapter6

import nuno.fp.chapter6.Exercise1.{RNG, nonNegativeInt}
import nuno.fp.chapter6.Exercise2.double

object Exercise3 {
  def doubleInt(rng: RNG): ((Double, Int), RNG) = {
    val ((i, d), g) = intDouble(rng)
    ((d, i), g)
  }

  def intDouble(rng: RNG): ((Int, Double), RNG) = {
    val (i, gen) = nonNegativeInt(rng)
    val (d, g) = double(gen)
    ((i, d), g)
  }

  def double3(rng: RNG): ((Double, Double, Double), RNG) = {
    val (d1, g1) = double(rng)
    val (d2, g2) = double(g1)
    val (d3, g3) = double(g2)
    ((d1, d2, d3), g3)
  }
}
