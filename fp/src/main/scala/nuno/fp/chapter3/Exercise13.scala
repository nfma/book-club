package nuno.fp.chapter3

import nuno.fp.chapter3.Exercise12.reverse

object Exercise13 {
  def foldLeft[A, B](as: List[A], z: B)(f: (B, A) => B): B = Exercise9.foldRight(reverse(as), z)((a, b) => f(b, a))

  def foldRight[A, B](as: List[A], z: B)(f: (A, B) => B): B = Exercise10.foldLeft(reverse(as), z)((b, a) => f(a, b))
}
