package nuno.fp.chapter4

import nuno.fp.chapter4.Exercise1.Option

object Exercise3 {
  def map2[A, B, C](a: Option[A], b: Option[B])(f: (A, B) => C): Option[C] = a flatMap {a => b map {b => f(a, b)}}
}
