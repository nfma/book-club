package fp.chapter2

object Exercise3 {
  def curry[A, B, C](f: (A, B) => C): A => (B => C) = (a: A) => (b: B) => f(a, b)
}
