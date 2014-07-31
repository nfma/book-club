package fp.chapter3

import fp.chapter3.Exercise10.foldLeft

object Exercise11 {
  def length(l: List[Int]) = foldLeft[Int, Int](l, 0)((b, a) => b + 1)

  def sum(ns: List[Int]) = foldLeft[Int, Int](ns, 0)(_ + _)

  def product(ns: List[Int]) = foldLeft[Int, Int](ns, 1)(_ * _)
}

