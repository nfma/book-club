package paul.fp.chapter6

import paul.fp.chapter6.Exercise1.Rand

object Exercise8 {
  def flatMap[A, B](f: Rand[A])(g: A => Rand[B]): Rand[B] = rng => {
    val (a, rng2) = f(rng)
    g(a)(rng2)
  }
}
