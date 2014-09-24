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

}


trait Stream[+A] {

  def take(n:Int): Stream[A] = iterate(this, Empty)(n, (i:Int) => i == n  )

  @tailrec
  private def iterate[B](s: => Stream[B], results:Stream[B])(i:Int,continue: Int => Boolean): Stream[B] = s match {
    case Empty     => results
    case _ if !continue(i) => results
    case Cons(h,t) if continue(i) => iterate[B](t(), Stream.cons[B](h(), results))(i, continue)
  }

  def drop(n:Int): Stream[A] = iterate2(this,Empty)

  @tailrec
  private def iterate2[B](s: => Stream[B], results:Stream[B]): Stream[B] = s match {
    case Empty     => results
    case Cons(h,t) => iterate2[B](t(), t())
  }

  def toList: List[A] = flatten[A](this, Nil)

  @tailrec
  private def flatten[B](s: => Stream[B], results:List[B]): List[B] = s match {
    case Empty     => results
    case Cons(h,t) => flatten[B](t(), results :+ h())
  }

  def headOption: Option[A] = this match {
    case Empty => None
    case Cons(h, t) => Some(h())
  }

}

case object Empty extends Stream[Nothing]

case class Cons[+A](h: () => A, t: () => Stream[A]) extends Stream[A]

