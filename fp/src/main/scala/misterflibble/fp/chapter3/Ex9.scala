package misterflibble.fp.chapter3
import misterflibble.fp.chapter3.Ex7._

object Ex9 {
  def length[A](l: List[A]): Int = foldRight(l,0)((a:A,acc:Int)=>acc+1)
}
