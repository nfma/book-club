package nuno.fp.chapter3

import nuno.fp.chapter3.Exercise2.tail

object Exercise4 {
  @annotation.tailrec
  def drop[A](l: List[A], n: Int): List[A] = n match {
    case a if a <= 0 => l
    case _ => drop(tail(l), n - 1)
  }
}
