package fp.chapter3

object Exercise9 {
  def foldRight[A, B](as: List[A], z: B)(f: (A, B) => B): B = as match {
    case Nil => z
    case Cons(x, xs) => f(x, foldRight(xs, z)(f))
  }

  def length[A](l: List[A]): Int = {
    foldRight[A, Int](l, 0)((a, b) => b + 1)
  }
}

