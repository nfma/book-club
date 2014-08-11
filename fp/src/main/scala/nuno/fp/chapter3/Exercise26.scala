package nuno.fp.chapter3

object Exercise26 {
  def maximum(t: Tree[Int]): Int = t match {
    case Leaf(l) => l
    case Branch(l, r) => maximum(l) max maximum(r)
  }
}
