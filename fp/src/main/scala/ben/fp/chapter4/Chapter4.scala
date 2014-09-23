package ben.fp.chapter4

import ben.fp.chapter3.{Cons, List, Nil}

object Chapter4 {

  trait Option[+A] {

    def map[B](f: A => B): Option[B] = this match {
      case Some(v) => Some(f(v))
      case None => None
    }

    def flatMap[B](f: A => Option[B]): Option[B] = map(f) getOrElse None

    def getOrElse[B >: A](default: => B): B = this match {
      case Some(v) => v
      case None => default
    }

    def orElse[B >: A](ob: => Option[B]): Option[B] = map(Some(_)) getOrElse ob

    def filter(f: A => Boolean): Option[A] = map {
      v => if (f(v)) Some(v) else None
    } getOrElse None

    def map2[A, B, C](a: Option[A], b: Option[B])(f: (A, B) => C): Option[C] =
      a flatMap (a => b map (b => f(a, b)))

    def mean(xs: Seq[Double]): Option[Double] =
      if (xs.isEmpty) None
      else Some(xs.sum / xs.length)

    def variance(xs: Seq[Double]): Option[Double] =
      mean(xs) flatMap (m => mean(xs.map(x => math.pow(x - m, 2))))

    def sequence[A](a: List[Option[A]]): Option[List[A]] = traverse(a)(identity)

    def traverse[A, B](a: List[A])(f: A => Option[B]): Option[List[B]] = a match {
      case Nil => None
      case _ => List.foldRight[A, Option[List[B]]](a, Some(Nil))((x, y) => map2(f(x), y)((t, y) => Cons(t, y)))
    }
  }

  case class Some[+A](get: A) extends Option[A]

  case object None extends Option[Nothing]

  trait Either[+E, +A] {

    def map[B](f: A => B): Either[E, B] = this match {
      case Right(r) => Right(f(r))
      case Left(e)  => Left(e)
    }

    def flatMap[EE >: E, B](f: A => Either[EE, B]): Either[EE, B]  = this map f match {
      case Left(e) => Left(e)
      case Right(r) => r
    }

    def orElse[EE >: E, B >: A](b: => Either[EE, B]): Either[EE, B] = this match {
      case Left(l) => b
      case Right(r) => Right(r)
    }

    def map2[EE >: E, B, C](b: Either[EE, B])(f: (A, B) => C): Either[EE, C] = flatMap( r => b.map(f(r,_)))

    def sequence[E, A](es: List[Either[E, A]]): Either[E, List[A]] = es match {
      case Nil => Right(Nil)
      case Cons(fail@Left(_), _) => fail
      case Cons(ok@Right(r), tail) =>  sequence(tail) map(o => Cons(r,o))
    }

    def traverse[E, A, B](as: List[A])(f: A => Either[E, B]): Either[E, List[B]] = as match {
      case Nil => Right(Nil)
      case _ => List.foldRight[A, Either[E, List[B]]](as, Right(Nil)){
        (a, results) => f(a) flatMap( bs => results.map(Cons(bs,_)))
      }
    }
  }

  case class Left[+E](value: E) extends Either[E, Nothing]

  case class Right[+A](value: A) extends Either[Nothing, A]
}






























