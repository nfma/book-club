package ben.fp.chapter4

import scala.annotation.tailrec

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


    def sequence[A](a: List[Option[A]]): Option[List[A]] = {

      @tailrec
      def iterate(l:List[Option[A]], results:Option[List[A]]) : Option[List[A]] = l match {
        case Nil => results
        case None :: tail => None
        case head@Some(v) :: tail => iterate(tail, results.map(l =>  l ++ List(v)))
      }

      a match {
        case Nil => None
        case head :: tail => iterate(tail, head.map( v => List(v)) )
      }
    }

    def traverse[A, B](a: List[A])(f: A => Option[B]): Option[List[B]] = {

      def process(l: List[A], results: Option[List[B]] = None): Option[List[B]] =
        l match {
          case Nil => results
          case x :: xs => process(xs, f(x).flatMap(i => results.map(_ :+ i) orElse Some(List(i)) ))
        }


      process(a)
    }
  }


  case class Some[+A](get: A) extends Option[A]

  case object None extends Option[Nothing]

}
