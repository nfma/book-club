package ben.fp.chapter2

object Chapter2 {

  def fib(n: Int): Int = n match {
    case num if n <= 0 => 0
    case num if n == 1 => 1
    case _ => fib(n-2) + fib(n-1)
  }

  def isSorted[A](as: Array[A], gt: (A,A) => Boolean): Boolean = {
    if(as.size <= 1) true
    else gt(as(0), as(1)) && isSorted(as.slice(1, as.length), gt)
  }

  def curry[A,B,C](f: (A, B) => C): A => (B => C) = (a:A) => b => f(a, b)

  def uncurry[A,B,C](f: A => B => C): (A, B) => C = (a,b) => f(a)(b)

  def compose[A,B,C](f: B => C, g: A => B): A => C = a => f(g(a))
}

