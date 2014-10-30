package nuno.fp.chapter7

import nuno.fp.chapter7.Exercise3.Par

object Exercise4 {

  def asyncF[A, B](f: A => B): A => Par[B] = a => Par.lazyUnit(f(a))
}
