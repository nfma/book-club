package misterflibble.fp.chapter2

object Ex1 {

  def fib(n: Int): Int = {
    @annotation.tailrec
    def go(n: Int, acc: Int): Int =
      n match {
        case 0 => acc
        case 1 => acc+1
        case 2 => acc+1
        case _ => go(n-1, fib(n-2)+acc)
      }

    go(n, 0)
  }

// not quite: 0, 0, 1, 2, 3, 5, ... wronk!
//  def fib2(n: Int): Int = {
//    @annotation.tailrec
//    def go(n: Int, acc: Int): Int = n match {
//      case x if x<2 => acc-1
//      case 2        => acc
//      case _        => go(n-1, fib(n-2)+acc)
//    }
//    go(n, 1)
//  }

}
