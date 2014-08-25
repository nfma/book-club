package nuno.fp.chapter5

import nuno.fp.chapter4.Exercise1.{Some, None, Option}
import nuno.fp.chapter5.Exercise1.{Cons, Empty, Stream}
import nuno.fp.chapter5.Exercise1.Stream.unfold


object Exercise13 {
  def map[A, B](as: Stream[A])(f: A => B): Stream[B] = unfold(as) {
    case Empty => None
    case Cons(h, tl) => Some((f(h()), tl()))
  }

  def take[A](as: Stream[A], n: Int): Stream[A] = unfold((n, as)) {
    case (nr, _) if nr <= 0 => None
    case (_, Empty) => None
    case (nr, Cons(h, t)) => Some((h(), (nr - 1, t())))
  }

  def takeWhile[A](as: Stream[A])(f: A => Boolean): Stream[A] = unfold(as) {
    case Empty => None
    case Cons(h, t) =>
      val hv = h() // avoid evaluating this value twice
      if (f(hv)) Some((hv, t())) else None
  }
}
