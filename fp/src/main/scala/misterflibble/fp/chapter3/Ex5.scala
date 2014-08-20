package misterflibble.fp.chapter3

object Ex5 {
  def dropWhile[A](l: List[A], f: A => Boolean): List[A] = {
    l match {
      case Nil => Nil
      case Cons(h, t)  => if (f(h)) dropWhile(t, f) else l
    }
  }
}