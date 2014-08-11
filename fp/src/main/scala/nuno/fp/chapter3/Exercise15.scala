package nuno.fp.chapter3

import nuno.fp.chapter3.Exercise13.foldRight
import nuno.fp.chapter3.Exercise14.append

object Exercise15 {
  def concat[A](l: List[List[A]]): List[A] = foldRight(l, Nil: List[A])((h, t) => append(h, t))
}
