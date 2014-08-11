package nuno.fp.chapter3

import nuno.fp.chapter3.Exercise10.foldLeft

object Exercise12 {
  def reverse[A](l: List[A]): List[A] = l match {
    case Cons(h, t) => foldLeft(t, Cons(h, Nil))((t, h) => Cons(h, t))
    case _ => Nil
  }
}
