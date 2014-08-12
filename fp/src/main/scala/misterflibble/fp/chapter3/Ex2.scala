package misterflibble.fp.chapter3

object Ex2 {
  def tail[A](l:List[A]) : List[A]=  l match {
    case Nil   => Nil
    case Cons(a,t) => t
  }
}
