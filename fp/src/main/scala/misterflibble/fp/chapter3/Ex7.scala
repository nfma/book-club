package misterflibble.fp.chapter3

object Ex7 {
  def foldRight[A,B](as: List[A], z: B)(f: (A, B) => B): B =
    as match {
      case Nil => z
      case Cons(x, xs) => f(x, foldRight(xs, z)(f))
    }

  def simpleProduct(l:List[Int]) = foldRight(l, 1)((i:Int,j:Int)=> if (i==0) 0 else i*j)
}
