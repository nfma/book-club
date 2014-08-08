package paul.chapter2

object PaulSolutionExercise1 {
  def fib(n: Int): Int = {
    @annotation.tailrec
    def loop(n: Int, prev: Int, cur: Int): Int =
      if (n == 0) prev
      else loop(n - 1, cur, prev + cur)
    loop(n, 0, 1)
  }
}

object PaulSolutionExercise2 {
  def isSorted[A](as: Array[A], fn: (A,A) => Boolean): Boolean = {

    @annotation.tailrec
    def loop(idx:Int, sorted:Boolean):Boolean =
      if (idx + 1 == as.length) sorted
      else if (!sorted) sorted
      else loop(idx+1, fn(as(idx), as(idx+1)))
    loop(0, true)
  }
}

object PaulSolutionExercise3 {
  def curry[A, B, C, D](f: (A, B) => C): A => (B => C) = (a: A) => (b: B) => f(a, b)
}

object PaulSolutionExercise4 {
  def uncurry[A, B, C](f: A => B => C): (A, B) => C = {
    (a: A, b: B) => {
      f(a)(b)
    }
  }
}

object PaulSolutionExercise5 {
  def compose[A, B, C](f: B => C, g: A => B): A => C = {
    (a: A) => f(g(a))
  }
}