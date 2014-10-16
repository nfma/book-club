package nuno.fp.chapter6

import nuno.fp.chapter6.Exercise1.{RNG, nonNegativeInt}

object Exercise5 {
  type Rand[+A] = RNG => (A, RNG)

  def double(rng: RNG): (Double, RNG) = map(nonNegativeInt) {_ / Int.MaxValue.toDouble} {rng}

  def map[A, B](s: Rand[A])(f: A => B): Rand[B] = rng => {
    val (a, rng2) = s(rng)
    (f(a), rng2)
  }
}
