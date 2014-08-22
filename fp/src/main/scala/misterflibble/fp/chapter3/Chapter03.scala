package misterflibble.fp.chapter3
import Ex10.foldLeft
import Ex7.foldRight

object Ex1 {

  import List._

  def ex1 = {
    val x = List(1, 2, 3, 4, 5) match {
      case Cons(x, Cons(2, Cons(4, _))) => x
      case Nil => 42
      case Cons(x, Cons(y, Cons(3, Cons(4, _)))) => x + y
      case Cons(h, t) => h + sum(t)
      case _ => 101
    }
    x
  }
}

object Ex2 {
  def tail[A](l:List[A]) : List[A]=  l match {
    case Nil   => Nil
    case Cons(a,t) => t
  }
}

object Ex3 {
  def setHead[A](x: A, xs: List[A]): List[A] = {
    xs match {
      case Nil => Cons(x, Nil)
      case Cons(h, t) => Cons(x, t)
    }
  }
}

object Ex4 {
  @annotation.tailrec
  def drop[A](l: List[A], n: Int): List[A] = l match {
    case Nil                 => Nil
    case Cons(x,t)  if n==0  => l
    case Cons(x,t)  if n>0   => drop(t, n-1)
  }
}

object Ex5 {
  def dropWhile[A](l: List[A], f: A => Boolean): List[A] = {
    l match {
      case Nil => Nil
      case Cons(h, t)  => if (f(h)) dropWhile(t, f) else l
    }
  }
}

object Ex6 {
  def init[A](l: List[A]): List[A] = l match {
    case Nil                  => Nil
    case Cons(h,Nil)          => Nil
    case Cons(h,t)            => Cons(h,init(t))
  }
}

object Ex7 {
  def foldRight[A,B](as: List[A], z: B)(f: (A, B) => B): B =
    as match {
      case Nil => z
      case Cons(x, xs) => f(x, foldRight(xs, z)(f))
    }

  def simpleProduct(l:List[Int]) = foldRight(l, 1)((i:Int,j:Int)=> if (i==0) 0 else i*j)
}

object Ex8 {
 // Nothing here...
}

object Ex9 {
  def length[A](l: List[A]): Int = foldRight(l,0)((a:A,acc:Int)=>acc+1)
}

object Ex10 {
  @annotation.tailrec
  def foldLeft[A,B](l: List[A], z: B)(f: (B, A) => B): B = l match {
    case Nil       => z
    case Cons(h,t) =>  foldLeft(t,f(z,h))(f)
  }
}

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
  def append[A](l:List[A], a:A):List[A] = foldRight(l,Cons(a,Nil))((x:A,y:List[A])=>Cons(x,y))
  //foldLeft[A,B](l: List[A], z: B)(f: (B, A) => B): B
//  def appendL[A](l:List[A], a:A):List[A] = l match {
//    case Nil       => Cons(a,Nil)
//    case Cons(h,t) => foldLeft(t,h)((x,y)=>(Cons(y,)))
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
