package ben.fp.chapter3

import scala.annotation.tailrec


sealed trait Tree[+A]

case class Leaf[A](value: A) extends Tree[A]

case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]

object Tree {

  def size[A](t:Tree[A]) : Int =
    fold[A, Int](t)(i => 1)( _ + _ + 1)

  def depth[A](t:Tree[A]) : Int =
    fold[A, Int](t)(i => 1)( (a,b) => (a+1) max (b+1))

  def map[A,B](t:Tree[A])(f:A=>B) : Tree[B] =
    fold[A, Tree[B]](t)(i => Leaf(f(i)))(Branch(_,_))

  def maximum[A <% Int](t:Tree[A]) : Int =
    fold[A, Int](t)( i => i )( _ max _ )

  def fold[A,B](t: Tree[A])(l: A => B)(b: (B,B) => B): B = t match {
    case Leaf(v)     => l(v)
    case Branch(left,right) => b(fold(left)(l)(b),fold(right)(l)(b))
  }
}