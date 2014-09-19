package nuno.fp.chapter3

object Exercise28 {
  def map[A, B](t: Tree[A])(f: A => B): Tree[B] = t match {
    case Leaf(v) => Leaf(f(v))
    case Branch(l, r) => Branch(map(l) {f}, map(r) {f})
  }
}
