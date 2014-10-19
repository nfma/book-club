package paul.fp.chapter5

import Stream._

object Exercise1 {

  trait Stream[+A] {

    def foldRight[B](z: => B)(f: (A, => B) => B): B =
      this match {
        case Cons(h, t) => {
          f(h(), t().foldRight(z)(f))
        }
        case _ => z
      }

    def exists(p: A => Boolean): Boolean =
      foldRight(false)((a, b) => p(a) || b) // Here `b` is the unevaluated recursive step that folds the tail of the stream. If `p(a)` returns `true`, `b` will never be evaluated and the computation terminates early.

    @annotation.tailrec
    final def find(f: A => Boolean): Option[A] = this match {
      case Empty => None
      case Cons(h, t) => if (f(h())) Some(h()) else t().find(f)
    }

    def take(n: Int): Stream[A] = {
      if (n <= 0)
        Stream()
      else this match {
        case Cons(h, t) =>
          Stream.cons(h(), t().take(n - 1))
        case _ =>
          Stream()
      }
    }

    def drop(n: Int): Stream[A] = {
        def dropRec (s: Stream[A], n: Int):Stream[A] = {
          if (n <= 0) {
            s
          }
          else s match {
            case Cons(h, t) => dropRec(t(), n - 1)
            case _ => Stream()
          }
        }
        dropRec(this, n)
    }

    def takeWhile(f: A => Boolean): Stream[A] = this match {
      case Cons(h,t) if f(h()) => Stream.cons(h(), t() takeWhile f)
      case _ => Stream()
    }

    def takeWhileFoldRight(f: A => Boolean): Stream[A] = {
      foldRight(Stream[A]())((a,b) =>
        if (f(a)) Stream.cons(a, b)
        else b
      )
    }

    def headOption: Option[A] = {
      foldRight(None: Option[A])((a, b) => Option(a))
    }

    def forAll(f: A => Boolean): Boolean =
      foldRight(true)((a,b) => { f(a) && b})

    def startsWith[A](s: Stream[A]): Boolean = sys.error("todo")

    def map[B](f: A => B): Stream[B] = {
      foldRight(Stream.empty[B])((h,t) => Stream.cons(f(h), t))
    }

    def filter[B](f: A => Boolean): Stream[A] = ???

    def append[B>:A](s: => Stream[B]): Stream[B] = ???

    def flatMap[B](f: A => Stream[B]): Stream[B] = ???

    def toList: List[A] = {

      def toListRec(stream: Stream[A], acc: List[A]): List[A] = stream match {
        case Cons(h, t) => toListRec(t(), h() :: acc)
        case Empty => acc
      }
      toListRec(this, Nil).reverse
    }
  }

  case object Empty extends Stream[Nothing]

  case class Cons[+A](h: () => A, t: () => Stream[A]) extends Stream[A]

  object Stream {
    def cons[A](hd: => A, tl: => Stream[A]): Stream[A] = {
      lazy val head = hd
      lazy val tail = tl
      Cons(() => head, () => tail)
    }

    def empty[A]: Stream[A] = Empty

    def apply[A](as: A*): Stream[A] =
      if (as.isEmpty) empty
      else cons(as.head, apply(as.tail: _*))

    val ones: Stream[Int] = Stream.cons(1, ones)

    def from(n: Int): Stream[Int] = sys.error("todo")

    def unfold[A, S](z: S)(f: S => Option[(A, S)]): Stream[A] = sys.error("todo")
  }

}
