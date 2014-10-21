package paul.fp.chapter6

import paul.fp.chapter6.Exercise1._

object Exercise6 {

  def map2[A, B, C](ra: Rand[A], rb: Rand[B])(f: (A, B) => C): Rand[C] = rng => {
    val (a, rng2) = ra(rng)
    val (b, rng3) = rb(rng2)
    (f(a, b), rng3)
  }

  def both[A, B](ra: Rand[A], rb: Rand[B]): Rand[(A, B)] =
    map2(ra, rb)((_, _))

  val randIntDouble: Rand[(Int, Int)] = both(nonNegativeInt, nonNegativeInt)

  def main(args: Array[String]): Unit = {
    println(randIntDouble)
    println(randIntDouble(Simple(42)))
  }
}
