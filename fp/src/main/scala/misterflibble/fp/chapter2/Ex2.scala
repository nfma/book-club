package misterflibble.fp.chapter2

object Ex2 {
  def isSorted[A](as: Array[A], gt: (A,A) => Boolean): Boolean = {
    @annotation.tailrec
    def loop(n: Int): Boolean =
      if (n < as.length-1)
        if (gt(as(n+1),as(n))) loop(n+1)
        else false
      else true
    if (as.length<2) true else loop(0)
  }

}
