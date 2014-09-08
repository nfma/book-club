package nuno.fp.chapter6

import nuno.fp.chapter6.Exercise5.Rand

object Exercise8 {
  def flatMap[A, B](f: Rand[A])(g: A => Rand[B]): Rand[B] = rng => {
    val (a, rng2) = f(rng)
    g(a)(rng2)
  }
}
