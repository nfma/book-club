package nuno.fp.chapter3

object Exercise22 {
  def zipSum(xs: List[Int], ys: List[Int]): List[Int] = (xs, ys) match {
    case (Cons(h1, t1), Cons(h2, t2)) => Cons(h1 + h2, zipSum(t1, t2))
    case _ => Nil
  }
}
