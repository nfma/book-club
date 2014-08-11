package nuno.fp.chapter3

import nuno.fp.chapter3.Exercise10.foldLeft

object Exercise11 {
  def sum(ints: List[Int]): Int = foldLeft(ints, 0)(_ + _)

  def product(ints: List[Int]): Int = foldLeft(ints, 1)(_ * _)

  def length[A](xs: List[A]): Int = foldLeft(xs, 0)((b, _) => b + 1)
}
