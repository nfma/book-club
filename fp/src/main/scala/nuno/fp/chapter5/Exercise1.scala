package nuno.fp.chapter5

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

    def headOption2(): Option[A] = foldRight[Option[A]](None)((a, b) => Some(a))

    def map[B](f: A => B): Stream[B] = foldRight[Stream[B]](Empty)((a, b) => Stream.cons(f(a), b))

    def flatMap[B](f: A => Stream[B]): Stream[B] = foldRight[Stream[B]](Empty)((a, b) => f(a).append(b))

    def filter(f: A => Boolean): Stream[A] = foldRight[Stream[A]](Empty)((a, b) => if(f(a)) Stream.cons(a, b) else b)

    def append[B >: A](s: Stream[B]): Stream[B] = foldRight[Stream[B]](s)((a, b) => Stream.cons(a, b))
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
  }

  case class Cons[+A](h: () => A, t: () => Stream[A]) extends Stream[A] {
    override def toList: List[A] = h() :: t().toList

    override def take(n: Int): Stream[A] = if (n <= 0) Empty else Stream.cons(h(), t().take(n - 1))

    override def drop(n: Int): Stream[A] = if (n <= 0) this else t().drop(n - 1)

    override def dropWhile(p: A => Boolean): Stream[A] = if (p(h())) t().dropWhile(p) else this

    override def takeWhile(p: A => Boolean): Stream[A] = foldRight[Stream[A]](Empty)((a, b) => if(p(a)) Stream.cons(a, b) else Empty)

    override def forAll(p: A => Boolean) = p(h()) && t().forAll(p)

    override def foldRight[B](z: => B)(f: (A, => B) => B): B = f(h(), t().foldRight(z)(f))

    override def headOption(): Option[A] = Some(h())
  }

  object Stream {
    def cons[A](hd: => A, tl: => Stream[A]): Stream[A] = {
      lazy val head = hd
      lazy val tail = tl
      Cons(() => head, () => tail)
    }

    def empty[A]: Stream[A] = Empty

    def apply[A](as: A*): Stream[A] = if (as.isEmpty) empty else cons(as.head, apply(as.tail: _*))

    def append[A](s1: Stream[A], s2: Stream[A]): Stream[A] = ???
  }

}
