package misterflibble.fp.chapter3

object Ex6 {
  def init[A](l: List[A]): List[A] = l match {
    case Nil                  => Nil
    case Cons(h,Cons(hh,Nil)) => Cons(h,Nil)
    case Cons(h,t)            => Cons(h,init(t))
  }
}
