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

  def constant[A](a:A): Stream[A] = cons(a, constant(a))

  def from(a:Int): Stream[Int] = cons(a, from(a + 1))

  def fibs: Stream[Int] = {
    def sequece(a:Int, b:Int): Stream[Int] =
      cons(a, sequece(b, a+b))
    sequece(0,1)
  }

  def unfold[A, S](z: S)(f: S => Option[(A, S)]): Stream[A] =
    f(z) map( r => cons(r._1, unfold(r._2)(f))) getOrElse empty[A]

  def append[S >: A, A](a1: Stream[A], a2: Stream[S]): Stream[S] = a1.foldRight(a2)((a,b) => cons(a, b))


  val ones = constant2(1)

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
    case Cons(h, t) if p(h()) => takeWhile(t(), p, cons(h(), results))
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
    case Cons(h, t) => reverse(t(), cons(h(), reversed))
  }
}


trait Stream[+A] {

  import Stream._

  def take(n: Int): Stream[A] = Stream take(this, n, Empty) reverse

  def reverse: Stream[A] = Stream reverse(this, Empty)

  def drop(n: Int): Stream[A] = Stream drop(this, n, Empty)

  def map[B](f: A => B): Stream[B] = this match {
    case Empty => Empty
    case Cons(h,t) => cons(f(h()), t().map(f))
  }

  def flatMap[B](f: A => Stream[B]): Stream[B] = this match {
    case Empty => Empty
    case Cons(h,t) => Stream.append(f(h()), t().flatMap(f))
  }

  def filter(f: A => Boolean): Stream[A] = foldRight(empty[A])((a,b) => if(f(a)) cons(a,b) else b)

  def foldRight[B](z: => B)(f: (A, => B) => B): B = this match {
    case Cons(h, t) => f(h(), t().foldRight(z)(f))
    case _ => z
  }

  def exists(p: A => Boolean): Boolean = foldRight(false)((a, b) => p(a) || b)

  def forAll(p: A => Boolean): Boolean = !exists(p(_) == false)

  def toList: List[A] = listify[A](this, List())

  def takeWhile(p: A => Boolean): Stream[A] = foldRight(empty[A])(
    (a,stream) => if (p(a)) cons(a, stream) else empty
  )

  def takeWhile1(p: A => Boolean): Stream[A] = Stream takeWhile(this, p, Empty) reverse

  def append[S >: A](other:Stream[S]) : Stream[S] = Stream.append[S,S](this, other)


  def map2[B](f: A => B): Stream[B] = unfold(this) {
    case Cons(h, t) => Some(f(h()), t())
    case _ => None
  }

  def take2(n: Int): Stream[A] = unfold((n, this)){
    case (_, Empty) => None
    case (0, _    ) => None
    case (n, Cons(h,t)) => Some(h(), n-1 -> t())
  }

  def takeWhile2(p: A => Boolean): Stream[A] = unfold(this){
    case Cons(h, t) if p(h()) => Some(h() -> t())
    case _ => None
  }

  def zipWith[J](s: Stream[J])(f:(A,J) => J): Stream[J] = unfold(this, s) {
    case (Cons(h,t), Cons(h2,t2)) => Some(f(h(),h2()), t() -> t2())
    case _ => None
  }

  def zipAll[B](s2: Stream[B]): Stream[(Option[A],Option[B])] = unfold(this -> s2) {
    case (Cons(h, t), Cons(h1, t1)) => Some(Some(h()) -> Some(h1()), t() -> t1())
    case (Empty, Cons(h1, t1)) => Some(Option.empty[A] -> Some(h1()), empty[A] -> t1())
    case (Cons(h, t), Empty) => Some(Some(h()) -> Option.empty[B], t() -> empty[B])
    case (Empty, Empty) => None
  }

  def startsWith[A](s: Stream[A]): Boolean = s match {
    case Empty => false
    case _ => zipAll(s).takeWhile(_._2.nonEmpty).forAll{case (h,h2) => h == h2}
  }

  def tails: Stream[Stream[A]] = unfold(this) {
    case Empty => None
    case a => Some(a, a drop 1)
  } append Stream(empty[A])

  def hasSubsequence[A](s: Stream[A]): Boolean = tails exists (_ startsWith s)

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

