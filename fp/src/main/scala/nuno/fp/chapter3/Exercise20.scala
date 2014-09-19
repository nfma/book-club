package nuno.fp.chapter3

import nuno.fp.chapter3.Exercise13.foldRight
import nuno.fp.chapter3.Exercise14.append

object Exercise20 {
  def flatMap[A, B](l: List[A])(f: A => List[B]): List[B] = foldRight(l, Nil: List[B]) {(h, t) => append(f(h), t)}
}
