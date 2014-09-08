package nuno.fp.chapter2

object Exercise1 {
  def fib(n: Long): Long = n match {
    case a if a < 1 => 0
    case 1 => 1
    case _ => fib(n - 1) + fib(n - 2)
  }
}
