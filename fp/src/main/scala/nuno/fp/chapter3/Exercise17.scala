package nuno.fp.chapter3

import nuno.fp.chapter3.Exercise13.foldRight

object Exercise17 {
  def doubleToString(l: List[Double]): List[String] = foldRight(l, Nil: List[String])((h, t) => Cons(h.toString, t))
}
