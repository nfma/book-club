package misterflibble.fp.chapter3

object Ex6 {
  def init[A](l: List[A]): List[A] = l match {
    case Nil                  => Nil
    case Cons(h,Nil)          => Nil
    case Cons(h,t)            => Cons(h,init(t))
  }
}
