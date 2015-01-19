package nuno.fp.chapter3

object Exercise13 {
  def foldLeft[A, B](as: List[A], z: B)(f: (B, A) => B): B = Exercise9.foldRight(as, identity[B] _) {(a, g) => b => g(f(b, a))}(z)

  def foldRight[A, B](as: List[A], z: B)(f: (A, B) => B): B = Exercise10.foldLeft(as, identity[B] _) {(g, a) => b => g(f(a, b))}(z)
}
