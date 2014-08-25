package nuno.fp.chapter5

import nuno.fp.chapter5.Exercise1.Stream
import nuno.fp.chapter5.Exercise1.Stream.cons

object Exercise10 {
  def fibs: Stream[Int] = {
    def f(n: Int, m: Int): Stream[Int] = cons(n, f(m, n + m))
    f(0, 1)
  }
}
