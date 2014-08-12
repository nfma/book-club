package misterflibble.fp.chapter3

object Ex3 {
  def setHead[A](x: A, xs: List[A]): List[A] = {
    xs match {
      case Nil => Cons(x, Nil)
      case Cons(h, t) => Cons(x, t)
    }
  }
}
