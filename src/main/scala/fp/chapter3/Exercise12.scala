package fp.chapter3

import fp.chapter3.Exercise10.foldLeft

object Exercise12 {
  def reverse[A](l: List[A]) = foldLeft[A, List[A]](l, Nil:List[A])((b, a) => Cons(a, b))
}

