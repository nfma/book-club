package nuno.fp.chapter4

import nuno.fp.chapter3.Exercise9.foldRight
import nuno.fp.chapter3.{Cons, List, Nil}
import nuno.fp.chapter4.Exercise6.{Either, Right}

object Exercise7 {
  def sequence[E, A](es: List[Either[E, A]]): Either[E, List[A]] = traverse(es) {identity}

  def traverse[E, A, B](as: List[A])(f: A => Either[E, B]): Either[E, List[B]] =
    foldRight[A, Either[E, List[B]]](as, Right(Nil)) {(r, o) => o.map2(f(r))((l, e) => Cons(e, l))}
}
