package nuno.fp.chapter3

object Exercise23 {
  def zipWith[A, B](xs: List[A], ys: List[A])(f: (A, A) => B): List[B] = (xs, ys) match {
    case (Cons(h1, t1), Cons(h2, t2)) => Cons(f(h1, h2), zipWith(t1, t2)(f))
    case _ => Nil
  }
}
