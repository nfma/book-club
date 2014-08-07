package fp.chapter2

object PaulSolutionExercise1 {
  def fib(number: Int): Int = {
    @annotation.tailrec
    def loop(number: Int, idx: Int): Int = {
      if (idx <= 0) idx
      else loop(number, idx - 1)
    }
    loop(number, 0)
  }
}

object PaulSolutionExercise3 extends App {
  def curry[A, B, C, D](f: (A, B) => C): A => (B => C) = (a: A) => (b: B) => f(a, b)
}

object PaulSolutionExercise4 extends App {
  def uncurry[A, B, C](f: A => B => C): (A, B) => C = {
    (a: A, b: B) => {
      f(a)(b)
    }
  }
}

object PaulSolutionExercise5 extends App {
  def compose[A, B, C](f: B => C, g: A => B): A => C = {
    (a: A) => f(g(a))
  }
}