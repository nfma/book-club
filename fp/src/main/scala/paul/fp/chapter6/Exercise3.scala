package paul.fp.chapter6

import paul.fp.chapter6.Exercise1.{RNG, nonNegativeInt}
import paul.fp.chapter6.Exercise2.double

object Exercise3 {
  def doubleInt(rng: RNG): ((Double, Int), RNG) = {
    val rng1 = rng.nextInt
    val double: (Double, RNG) = Exercise2.double(rng1._2)
    ((double._1, rng1._1), double._2)
  }

  def intDouble(rng: RNG): ((Int, Double), RNG) = {
    val (i1, rng1) = rng.nextInt
    val (d1, rng2) = double(rng1)
    ((i1, d1), rng2)
  }

  def double3(rng: RNG): ((Double, Double, Double), RNG) = {
   val (d1, rng1) = double(rng)
   val (d2, rng2) = double(rng1)
   val (d3, rng3) = double(rng2)
   ((d1,d2,d3), rng3)
  }

}
