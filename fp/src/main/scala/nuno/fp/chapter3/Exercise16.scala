package nuno.fp.chapter3

import nuno.fp.chapter3.Exercise13.foldRight

object Exercise16 {
  def addOne(l: List[Int]): List[Int] = foldRight(l, Nil: List[Int]) {(h, t) => Cons(h + 1, t)}
}
