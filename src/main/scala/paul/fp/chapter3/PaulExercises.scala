package paul.fp.chapter3

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
  def drop[A](l: List[A], n: Int): List[A] = ???
}

object PaulSolutionExercise5 {
  def dropWhile[A](l: List[A], f: A => Boolean): List[A] = ???
}

object PaulSolutionExercise6 {
  def init[A](l: List[A]): List[A] = ???
}

object PaulSolutionExercise7 {

}

object PaulSolutionExercise8 {

}

object PaulSolutionExercise9 {
  def length[A](l: List[A]): Int = ???
}
