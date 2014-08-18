package nuno.fp.chapter4

import nuno.fp.chapter3.Exercise13._
import nuno.fp.chapter3.{Cons, List, Nil}
import nuno.fp.chapter4.Exercise1.{Some, Option, None}
import nuno.fp.chapter4.Exercise3._

object Exercise5 {
  def traverse[A, B](a: List[A])(f: A => Option[B]): Option[List[B]] = a match {
    case Nil => None
    case _ => foldRight[A, Option[List[B]]](a, Some(Nil))((r, o) => map2(f(r), o)(Cons(_, _)))
  }

  def sequence[A](a: List[Option[A]]): Option[List[A]] = traverse(a)(identity)
}
