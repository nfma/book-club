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

  def constant[A](a:A): Stream[A] = Stream.cons(a, constant(a))

  def from(a:Int): Stream[Int] = Stream.cons(a, from(a + 1))

  def fibs: Stream[Int] = {
    def sequece(a:Int, b:Int): Stream[Int] =
      cons(a, sequece(b, a+b))
    sequece(0,1)
  }

  def unfold[A, S](z: S)(f: S => Option[(A, S)]): Stream[A] =
    f(z) map( r => cons(r._1, unfold(r._2)(f))) getOrElse Stream.empty[A]

  def append[S >: A, A](a1: Stream[A], a2: Stream[S]): Stream[S] = a1.foldRight(a2)((a,b) => Stream.cons(a, b))


  val ones = constant(1)

  def constant2[A](a:A): Stream[A] = unfold(a)(_ => Some(a, a))

  def from2(a:Int): Stream[Int] = unfold(a)(i => Some(i, i+1))

  def fibs2: Stream[Int] = {
    def sequence(a:Int, b:Int): (Int, Int) = (b, a+b)
    unfold(0,1)( a => Some(a._1, sequence(a._1,a._2)) )
  }



  @tailrec
  private def takeWhile[A](s: Stream[A], p: A => Boolean, results: Stream[A]): Stream[A] = s match {
    case Empty => results
    case Cons(h, t) if !p(h()) => results
    case Cons(h, t) if p(h()) => takeWhile(t(), p, Stream.cons(h(), results))
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

  def take(n: Int): Stream[A] = Stream take(this, n, Empty) reverse

  def reverse: Stream[A] = Stream reverse(this, Empty)

  def drop(n: Int): Stream[A] = Stream drop(this, n, Empty)

  def map[B](f: A => B): Stream[B] = this match {
    case Empty => Empty
    case Cons(h,t) => Stream.cons(f(h()), t().map(f))
  }

  def flatMap[B](f: A => Stream[B]): Stream[B] = this match {
    case Empty => Empty
    case Cons(h,t) => Stream.append(f(h()), t().flatMap(f))
  }

  def filter(f: A => Boolean): Stream[A] = foldRight(Stream.empty[A])((a,b) => if(f(a)) Stream.cons(a,b) else b)

  def foldRight[B](z: => B)(f: (A, => B) => B): B = this match {
    case Cons(h, t) => f(h(), t().foldRight(z)(f))
    case _ => z
  }

  def exists(p: A => Boolean): Boolean = foldRight(false)((a, b) => p(a) || b)

  def forAll(p: A => Boolean): Boolean = !exists(p(_) == false)

  def toList: List[A] = listify[A](this, Nil)

  def takeWhile(p: A => Boolean): Stream[A] = foldRight(Stream.empty[A])(
    (a,stream) => if (p(a)) Stream.cons(a, stream) else Stream empty
  )

  def takeWhile1(p: A => Boolean): Stream[A] = Stream takeWhile(this, p, Empty) reverse

  def append[S >: A](other:Stream[S]) : Stream[S] = Stream.append[S,S](this, other)

  @tailrec
  private def listify[B](s: => Stream[B], results: List[B]): List[B] = s match {
    case Empty => results
    case Cons(h, t) => listify[B](t(), results :+ h())
  }

  def headOption: Option[A] = this match {
    case Empty => None
    case Cons(h, t) => Some(h())
  }

  def headOption2: Option[A] = foldRight[Option[A]](None) {
    case (a, b) => Some(a)
    case _ => None
  }

}

case object Empty extends Stream[Nothing]

case class Cons[+A](h: () => A, t: () => Stream[A]) extends Stream[A]

