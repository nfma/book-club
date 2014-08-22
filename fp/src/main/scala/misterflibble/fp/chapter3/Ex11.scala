package misterflibble.fp.chapter3
import Ex10.foldLeft
import Ex7.foldRight

object Ex11 {
  //EXERCISE 11: Write sum, product, and a function to compute the length of a list using foldLeft.

  def sum(l: List[Int]): Int = foldLeft(l, 0)((a:Int, b:Int) => (a + b))

  def product(l: List[Int]): Int = if (l==Nil) 0 else foldLeft(l, 1)((a:Int, b:Int) => (a * b))

  def length(l: List[Int]): Int = foldLeft(l, 0)((a:Int, b:Int) => (a + 1))
}
object Ex12 {
  //EXERCISE 12: Write a function that returns the reverse of a list (so given List(1,2,3) it returns
  //List(3,2,1)). See if you can write it using a fold.

  def reverse[A](l:List[A]):List[A] = l match {
    case Cons(h,t) => foldLeft(t,Cons(h, Nil))((a,b)=>Cons(b,a))
    case Nil       => Nil
  }
}

object Ex13 {
  //EXERCISE 13 (hard, optional): Can you write foldLeft in terms of foldRight?
  //How about the other way around? Implementing foldRight via foldLeft is useful because it lets us
  //implement foldRight tail-recursively, which means it works even for large lists without overflowing the stack.
  def foldLeft2[A,B](l: List[A], z: B)(f: (B, A) => B): B = ???
  def foldRight2[A,B](as: List[A], z: B)(f: (A, B) => B): B = ???
}

object Ex14 {
  //EXERCISE 14: Implement append in terms of either foldLeft or foldRight.
 // def append2[A](l:List[A], a:A) = foldRight(l,List(a))(l,r)=>(Cons(l,r))
//  def append[A](l:List[A], a:A):List[A] = l match {
//    case Cons(h,t) => foldLeft(l,Cons(h, Nil))((a,b)=>Cons(b,a))
//    case Nil       => Cons(a,Nil)
//  }
}

object Ex15 {
  //EXERCISE 15 (hard): Write a function that concatenates a list of lists into a single list. Its runtime
  //should be linear in the total length of all lists. Try to use functions we have already defined.
}

object Ex16 {
  //EXERCISE 16: Write a function that transforms a list of integers by adding 1 to each element.
  //(Reminder: this should be a pure function that returns a new List!)
}

object Ex17 {
  //EXERCISE 17: Write a function that turns each value in a List[Double] into a String. You can use
  //the expression d.toString to convert some d: Double to a String.
}

object Ex18 {
  //EXERCISE 18: Write a function map that generalizes modifying each element in a list while maintaining
  //the structure of the list. Here is its signature:
  def map[A,B](l: List[A])(f: A=>B):List[B] = ???
}
