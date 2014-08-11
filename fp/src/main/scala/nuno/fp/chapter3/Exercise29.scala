package nuno.fp.chapter3

object Exercise29 {
  def fold[A, B](t: Tree[A])(f: A => B, g: (B, B) => B): B = t match {
    case Leaf(a) => f(a)
    case Branch(l, r) => g(fold(l)(f, g), fold(r)(f, g))
  }
}
