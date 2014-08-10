package nuno.fp.chapter2

object Exercise2 {
  def isSorted[A](as: Array[A], gt: (A, A) => Boolean): Boolean = {
    @annotation.tailrec
    def loop(n: Int, result: Boolean): Boolean =
      if (!result || n < 1)
        result
      else
        loop(n - 1, result && gt(as(n - 1), as(n)))
    loop(as.length - 1, result = true)
  }
}
