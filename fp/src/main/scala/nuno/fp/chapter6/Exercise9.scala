package nuno.fp.chapter6

import nuno.fp.chapter6.Exercise5._
import nuno.fp.chapter6.Exercise8.flatMap

object Exercise9 {
  def map2[A, B, C](ra: Rand[A], rb: Rand[B])(f: (A, B) => C): Rand[C] = flatMap(ra) {a => rng => {
    val (b, rng2) = rb(rng)
    (f(a, b), rng2)
  }}

  def map[A, B](s: Rand[A])(f: A => B): Rand[B] = flatMap(s) {a => rng => {
    (f(a), rng)
  }}
}
