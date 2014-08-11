package nuno.fp.chapter3

import nuno.fp.chapter3.Exercise10.foldLeft
import nuno.fp.chapter3.Exercise11.length
import nuno.fp.chapter3.Exercise12.reverse
import nuno.fp.chapter3.Exercise14.append
import nuno.fp.chapter3.Exercise18.map
import nuno.fp.chapter3.Exercise19.filter

object Exercise24 {
  def hasSubsequence[A](l: List[A], sub: List[A]): Boolean = length(filter(generateSubsequences(l))(_ == sub)) > 0

  private def generateSubsequences[A](l: List[A]): List[List[A]] = l match {
    case Cons(h, t) => append(map(foldLeft(l, Nil: List[List[A]])((t, h) => t match {
      case Cons(sh, st) => Cons(Cons(h, sh), t)
      case _ => Cons(Cons(h, Nil), t)
    }))(reverse), generateSubsequences(t))

    case _ => Nil
  }
}
