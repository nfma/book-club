package misterflibble.fp.chapter3

object Ex4 {
  @annotation.tailrec
  def drop[A](l: List[A], n: Int): List[A] = l match {
    case Nil                 => Nil
    case Cons(x,t)  if n==0  => l
    case Cons(x,t)  if n>0   => drop(t, n-1)
  }
}
