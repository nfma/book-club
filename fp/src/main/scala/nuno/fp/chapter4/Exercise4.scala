package nuno.fp.chapter4

import nuno.fp.chapter3.Exercise13.foldRight
import nuno.fp.chapter3.{Cons, List, Nil}
import nuno.fp.chapter4.Exercise1.{None, Option, Some}
import nuno.fp.chapter4.Exercise3.map2

object Exercise4 {
  def sequence[A](a: List[Option[A]]): Option[List[A]] = a match {
    case Nil => None
    case _ => foldRight[Option[A], Option[List[A]]](a, Some(Nil))((r, o) => map2(r, o)(Cons(_, _)))
  }
}
