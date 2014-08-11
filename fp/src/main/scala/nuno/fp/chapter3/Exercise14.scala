package nuno.fp.chapter3

import nuno.fp.chapter3.Exercise13.foldRight

object Exercise14 {
  def append[A](xs: List[A], ys: List[A]): List[A] = foldRight(xs, ys)((h, t) => Cons(h, t))
}
