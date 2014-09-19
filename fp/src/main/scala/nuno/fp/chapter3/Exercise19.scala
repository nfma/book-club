package nuno.fp.chapter3

import nuno.fp.chapter3.Exercise13.foldRight

object Exercise19 {
  def filter[A](l: List[A])(f: A => Boolean): List[A] = foldRight(l, Nil: List[A]) {(h, t) => if (f(h)) Cons(h, t) else t}
}
