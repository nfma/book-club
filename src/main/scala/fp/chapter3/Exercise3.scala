package fp.chapter3

import fp.chapter3.Exercise2.tail

object Exercise3 {
  def setHead[A](x: A, xs: List[A]): List[A] = Cons(x, tail(xs))
}
