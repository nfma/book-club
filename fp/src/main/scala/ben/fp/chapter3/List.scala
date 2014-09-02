package ben.fp.chapter3

import scala.annotation.tailrec

sealed trait List[+A]

case object Nil extends List[Nothing]

case class Cons[+A](head: A, tail: List[A]) extends List[A]

object List {

  def sum(ints: List[Int]): Int = foldLeft(ints, 0)(_ + _)

  def product(ds: List[Double]): Double = foldLeft(ds, 1.0)(_ * _)

  def foldRight[A,B](as: List[A], z: B)(f: (A, B) => B): B =
    as match {
      case Nil => z
      case Cons(x, xs) => f(x, foldRight(xs, z)(f))
    }

  @tailrec
  def foldLeft[A,B](l: List[A], z: B)(f: (B, A) => B): B = l match {
    case Nil => z
    case Cons(x,xs) => foldLeft(xs, f(z,x))(f)
  }

  def foldRightUsingFoldLeft[A,B](as: List[A], z: B)(f: (A, B) => B): B = foldLeft(reverse(as), z)((b,a)=>f(a,b))

  def foldLeftUsingFoldRight[A,B](l: List[A], z: B)(f: (B, A) => B): B = foldRight(reverse(l), z)((b,a)=>f(a,b))

  def sum2(ns: List[Int]) =
    foldRight(ns, 0)((x,y) => x + y)

  def product2(ns: List[Double]) =
    foldRight(ns, 1.0)(_ * _)

  def apply[A](as: A*): List[A] =
    if (as.isEmpty) Nil
    else Cons(as.head, apply(as.tail: _*))

  def tail[A](l:List[A]) : List[A] = l match {
    case Nil => Nil
    case Cons(a, rest) => rest
  }

  def setHead[A](head: A, xs: List[A]) = xs match {
    case Nil => List(head)
    case Cons(x, tail) => Cons(head, tail)
  }

  def drop[A](l: List[A], n: Int): List[A] = n match {
    case n if n <=0 => l
    case n => drop(tail(l), n-1)
  }

  def dropWhile[A](l: List[A])(f: A => Boolean): List[A] = l match {
    case Cons(head, tail) if f(head) => dropWhile(tail)(f)
    case _ => l
  }

  def init[A](l: List[A]): List[A] = l match {
    case Nil => Nil
    case Cons(a, Nil) => Nil
    case Cons(a, t) => Cons(a,init(t))
  }

  def length[A](l: List[A]): Int = foldRight(l, 0) ((a, b) => b + 1)

  def reverse[A](l:List[A]) : List[A] = foldLeft(l, Nil:List[A])( (xs,x) => Cons(x , xs))


  def appendNonOptimised[A](a1: List[A], a2: List[A]): List[A] =
    a1 match {
      case Nil => a2
      case Cons(h,t) => Cons(h, appendNonOptimised(t, a2))
    }

  // Cons(a, Cons(b, Nil))
  // Cons(c,  Nil)
  //

  def append[A](a1: List[A], a2: List[A]): List[A] = foldLeft(a1,a2)( (a,b) => Cons(b,a))

  // this doesn;t look linear, more exponential
  def concatenate[A](a1: List[List[A]]): List[A] = reverse(foldLeft(a1,Nil:List[A])((a,b) => append(b,a)))
}