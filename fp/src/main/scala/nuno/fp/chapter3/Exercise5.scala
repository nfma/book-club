package nuno.fp.chapter3

object Exercise5 {
  @annotation.tailrec
  def dropWhile[A](l: List[A], f: A => Boolean): List[A] = l match {
    case Cons(h, t) => if (f(h)) dropWhile(t, f) else l
    case _ => Nil
  }
}
