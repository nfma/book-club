package nuno.fp.chapter3

import nuno.fp.chapter3.Exercise13.foldRight

object Exercise18 {
  def map[A, B](l: List[A])(f: A => B): List[B] = foldRight(l, Nil: List[B])((h, t) => Cons(f(h), t))
}
