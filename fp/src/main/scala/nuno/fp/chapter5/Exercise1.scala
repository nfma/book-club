package nuno.fp.chapter5

import nuno.fp.chapter4.Exercise1.{None, Option, Some}
import nuno.fp.chapter5.Exercise1.Stream.{cons, empty, unfold}

import scala.annotation.tailrec

object Exercise1 {

  sealed trait Stream[+A] {
    def toList: List[A]

    def take(n: Int): Stream[A]

    def drop(n: Int): Stream[A]

    def dropWhile(p: A => Boolean): Stream[A]

    def takeWhile(p: A => Boolean): Stream[A]

    def forAll(p: A => Boolean): Boolean

    def foldRight[B](z: => B)(f: (A, => B) => B): B

    def headOption(): Option[A]

    def headOption2(): Option[A] = foldRight[Option[A]](None) {(a, _) => Some(a)}

    def map[B](f: A => B): Stream[B] = foldRight[Stream[B]](empty) {(a, b) => cons(f(a), b)}

    def flatMap[B](f: A => Stream[B]): Stream[B] = foldRight[Stream[B]](empty) {(a, b) => f(a).append(b)}

    def filter(f: A => Boolean): Stream[A] = foldRight[Stream[A]](empty) {
      case (a, b) if f(a) => cons(a, b)
      case (_, b) => b
    }

    def append[B >: A](s: Stream[B]): Stream[B] = foldRight[Stream[B]](s) {cons(_, _)}

    def zipWith[B >: A, C](s: Stream[B])(f: (A, B) => C): Stream[C] = unfold((this, s)) {
      case (Empty, _) => None
      case (_, Empty) => None
      case (Cons(h1, t1), Cons(h2, t2)) => Some((f(h1(), h2()), (t1(), t2())))
    }

    def zipAll[B](s: Stream[B]): Stream[(Option[A], Option[B])] = unfold((this, s)) {
      // if you see the code below red in intellij, and want it to display nicely,
      // try helping their compiler with the type annotations for the first some on the second line
      case (Empty, Empty) => Some(((None, None), (Empty, Empty)))
      case (Empty, Cons(h, t)) => Some(((None, Some(h())), (Empty, t())))
      case (Cons(h, t), Empty) => Some(((Some(h()), None), (t(), Empty)))
      case (Cons(h1, t1), Cons(h2, t2)) => Some(((Some(h1()), Some(h2())), (t1(), t2())))
    }

    def startsWith[B](s: Stream[B]): Boolean

    def tails: Stream[Stream[A]] = unfold(this) {
      case s@Cons(_, t) => Some((s, t()))
      case _ => None
    }

    def scanRight[B](z: B)(f: (A, B) => B): Stream[B]
  }

  case object Empty extends Stream[Nothing] {
    override def toList: List[Nothing] = Nil

    override def take(n: Int): Stream[Nothing] = this

    override def drop(n: Int): Stream[Nothing] = this

    override def dropWhile(p: Nothing => Boolean): Stream[Nothing] = this

    override def takeWhile(p: Nothing => Boolean): Stream[Nothing] = this

    override def forAll(p: Nothing => Boolean) = true

    override def foldRight[B](z: => B)(f: (Nothing, => B) => B): B = z

    override def headOption(): Option[Nothing] = None

    override def startsWith[B](s: Stream[B]): Boolean = false

    override def scanRight[B](z: B)(f: (Nothing, B) => B): Stream[B] = Stream(z)
  }

  case class Cons[+A](h: () => A, t: () => Stream[A]) extends Stream[A] {
    override def toList: List[A] = h() :: t().toList

    override def take(n: Int): Stream[A] = if (n <= 0) empty else cons(h(), t().take(n - 1))

    override def drop(n: Int): Stream[A] = if (n <= 0) this else t() drop (n - 1)

    override def dropWhile(p: A => Boolean): Stream[A] = if (p(h())) t() dropWhile p else this

    override def takeWhile(p: A => Boolean): Stream[A] = foldRight[Stream[A]](empty) {
      case (a, b) if p(a) => cons(a, b)
      case _ => empty
    }

    override def forAll(p: A => Boolean) = p(h()) && t().forAll {p}

    override def foldRight[B](z: => B)(f: (A, => B) => B): B = f(h(), t().foldRight(z) {f})

    override def headOption(): Option[A] = Some(h())

    override def startsWith[B](s: Stream[B]): Boolean = s != empty && zipWith(s) {_ == _}.forAll {_ == true}

    override def scanRight[B](z: B)(f: (A, B) => B): Stream[B] = foldRight(Stream(z)) {
      case (a, s@Cons(hd, _)) => cons(f(a, hd()), s)
    }
  }

  object Stream {
    def cons[A](hd: => A, tl: => Stream[A]): Stream[A] = {
      lazy val head = hd
      lazy val tail = tl
      Cons(() => head, () => tail)
    }

    def empty[A]: Stream[A] = Empty

    def apply[A](as: A*): Stream[A] = if (as.isEmpty) empty else cons(as.head, apply(as.tail: _*))

    def constant[A](a: A): Stream[A] = cons[A](a, constant(a))

    def from(n: Int): Stream[Int] = cons[Int](n, from(n + 1))

    def unfold[A, S](z: S)(f: S => Option[(A, S)]): Stream[A] = f(z) match {
      case None => empty
      case Some((a, s)) => cons(a, unfold(s) {f})
    }
  }
}
