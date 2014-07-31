package fp.chapter2

object Exercise1 {
  def fib(n: Int): Int = n match {
    case 2 => 1
    case a if a < 2 => 0
    case _ => fib(n-1) + fib(n-2)
  }
}
