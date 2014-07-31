package fp.chapter3

object Exercise2 {
  def tail[A](xs: List[A]): List[A] = xs match {
    case Cons(h, t) => t
    case _ => Nil
  }
}
