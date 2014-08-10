package nuno.fp.chapter3

import nuno.fp.chapter3.Exercise2._

object Exercise3 {
  def setHead[A](x: A, xs: List[A]): List[A] = Cons(x, tail(xs))
}
