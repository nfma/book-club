package misterflibble.fp.chapter3

object Ex2 {
  import Ex1._
  def tail[A](l:List[A]) : List[A]=  l match {
    case Ex1.Nil   => Ex1.Nil
    case Cons(a,t) => t
  }
}
