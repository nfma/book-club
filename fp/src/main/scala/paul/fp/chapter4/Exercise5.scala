package paul.fp.chapter4

object Exercise5 {

  def traverse[A, B](a: List[A])(f: A => Option[B]): Option[List[B]] =
    a match {
      case Nil => Some(Nil)
      case h::t => {

        val traverse1: Option[List[B]] = traverse(t)(f)
        val f1: Option[B] = f(h)
        Exercise3.map2(f1, traverse1)(_ :: _)
      }
    }
}
