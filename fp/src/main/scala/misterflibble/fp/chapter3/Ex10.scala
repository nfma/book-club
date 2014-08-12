package misterflibble.fp.chapter3


object Ex10 {
  @annotation.tailrec
  def foldLeft[A,B](l: List[A], z: B)(f: (B, A) => B): B = l match {
    case Nil       => z
    case Cons(h,t) =>  foldLeft(t,f(z,h))(f)
  }
}
