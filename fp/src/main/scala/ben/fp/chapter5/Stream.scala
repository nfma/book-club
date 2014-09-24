package ben.fp.chapter5

import scala.annotation.tailrec

object Stream {

  def cons[A](hd: => A, tl: => Stream[A]): Stream[A] = {
    lazy val head = hd
    lazy val tail = tl
    Cons(() => head, () => tail)
  }

  def empty[A]: Stream[A] = Empty

  def apply[A](as: A*): Stream[A] =
    if (as.isEmpty) empty else cons(as.head, apply(as.tail: _*))

  @tailrec
  private def take[A](s: Stream[A], n: Int, results: Stream[A]): Stream[A] = s match {
    case Empty => results
    case _ if n <= 0 => results
    case Cons(h, t) => take(t(), n - 1, Stream.cons(h(), results))
  }

  @tailrec
  private def drop[A](s: Stream[A], n: Int, results: Stream[A]): Stream[A] = s match {
    case Empty => results
    case _ if n <= 0 => results
    case Cons(h, t) => drop(t(), n - 1, t())
  }

  @tailrec
  private def reverse[C](s: Stream[C], reversed: Stream[C] = Empty): Stream[C] = s match {
    case Empty => reversed
    case Cons(h, t) => reverse(t(), Stream.cons(h(), reversed))
  }
}


trait Stream[+A] {

  def take(n: Int): Stream[A] =  Stream take(this, n, Empty) reverse

  def reverse: Stream[A] = Stream reverse(this, Empty)

  def drop(n: Int): Stream[A] = Stream drop(this, n, Empty)

  def toList: List[A] = listify[A](this, Nil)

  @tailrec
  private def listify[B](s: => Stream[B], results: List[B]): List[B] = s match {
    case Empty => results
    case Cons(h, t) => listify[B](t(), results :+ h())
  }

  def headOption: Option[A] = this match {
    case Empty => None
    case Cons(h, t) => Some(h())
  }

}

case object Empty extends Stream[Nothing]

case class Cons[+A](h: () => A, t: () => Stream[A]) extends Stream[A]

