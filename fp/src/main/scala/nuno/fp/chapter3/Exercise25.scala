package nuno.fp.chapter3

sealed trait Tree[+A]

case class Leaf[A](value: A) extends Tree[A]

case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]

object Exercise25 {
  def size[A](t: Tree[A]): Int = t match {
    case Leaf(_) => 1
    case Branch(a, b) => 1 + size(a) + size(b)
  }
}
