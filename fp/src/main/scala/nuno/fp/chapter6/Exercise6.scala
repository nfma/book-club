package nuno.fp.chapter6

import nuno.fp.chapter6.Exercise5.Rand

object Exercise6 {
  def map2[A, B, C](ra: Rand[A], rb: Rand[B])(f: (A, B) => C): Rand[C] = rnd => {
    val (v1, rnd2) = ra(rnd)
    val (v2, rnd3) = rb(rnd2)
    (f(v1, v2), rnd3)
  }
}
