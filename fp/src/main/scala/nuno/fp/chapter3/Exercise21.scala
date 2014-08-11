package nuno.fp.chapter3

import nuno.fp.chapter3.Exercise20.flatMap

object Exercise21 {
  def filter[A](l: List[A])(f: A => Boolean): List[A] = flatMap(l)(e => if (f(e)) Cons(e, Nil) else Nil)
}
