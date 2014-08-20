package misterflibble.fp.chapter2

import org.scalatest.{Matchers, FunSpec}
import misterflibble.fp.chapter3._
import misterflibble.fp.chapter3.Ex2._

class Ch03Spec extends FunSpec with Matchers {
  describe("Ex 1") {
    describe("matching exercise") {
      it("should just evaluate") {
        misterflibble.fp.chapter3.Ex1.ex1 should equal(3)
      }
    }
  }

  describe("Ex 2") {
    it("tail") {
      tail(Cons(1,Cons(2,Cons(3,Nil)))) should equal(Cons(2,Cons(3,Nil)))
      tail(Nil) should equal(Nil)
    }
  }

  describe("Ex 3") {
    import misterflibble.fp.chapter3.Ex3._
    it("setHead") {
      setHead[Int](0, Nil) should equal(Cons(0, Nil))
      setHead[Int](9, Cons(0, Nil)) should equal(Cons(9,Nil))
      setHead[Int](9, Cons(0, Cons(1, Nil))) should equal(Cons(9,Cons(1, Nil)))
    }
  }

  describe("Ex 4") {
    import misterflibble.fp.chapter3.Ex4._
    it("drop n") {                              // scalacheck bait...
      drop[Int](Nil,0) should equal(Nil)
      drop[Int](Nil,2) should equal(Nil)

      drop[Int](Cons(1,Nil),0) should equal(Cons(1,Nil))
      drop[Int](Cons(1,Nil),1) should equal(Nil)

      drop[Int](Cons(2,Cons(1,Nil)),0) should equal(Cons(2,Cons(1,Nil)))
      drop[Int](Cons(2,Cons(1,Nil)),1) should equal(Cons(1,Nil))
      drop[Int](Cons(2,Cons(1,Nil)),2) should equal(Nil)

      drop[Int](Cons(3,Cons(2,Cons(1,Nil))),0) should equal(Cons(3,Cons(2,Cons(1,Nil))))
      drop[Int](Cons(3,Cons(2,Cons(1,Nil))),1) should equal(Cons(2,Cons(1,Nil)))
      drop[Int](Cons(3,Cons(2,Cons(1,Nil))),2) should equal(Cons(1,Nil))
      drop[Int](Cons(3,Cons(2,Cons(1,Nil))),3) should equal(Nil)
    }
  }

  describe("Ex 5") {
    import misterflibble.fp.chapter3.Ex5._
    /**
     * See also:  http://www.haskell.org/ghc/docs/6.12.2/html/libraries/base-4.2.0.1/Data-List.html#v%3AdropWhile
     *      and:  https://docs.python.org/2/library/itertools.html#itertools.dropwhile
     */
    it("dropWhile") {                             // scalacheck bait...
      def tt(i:Int) = true
      def ff(i:Int) = false
      def odd(i:Int):Boolean    = i%2==1
      def even(i:Int):Boolean   = !odd(i)
      val l5 = List(1,2,3,4,5)

      dropWhile(Nil, tt)  should equal(Nil)
      dropWhile(Nil, ff)  should equal(Nil)
      dropWhile(Nil, odd) should equal(Nil)
      dropWhile(Nil, even) should equal(Nil)

      dropWhile(l5, odd)  should equal(List(2,3,4,5))
      dropWhile(l5, even) should equal(l5)
      dropWhile(l5, ff)   should equal(l5)
      dropWhile(l5, tt)   should equal(Nil)

      dropWhile(List(1,3,5,7,9,2,4,6,1,3), even) should equal(List(1,3,5,7,9,2,4,6,1,3))
      dropWhile(List(1,3,5,7,9,2,4,6,1,3), odd)  should equal(List(2,4,6,1,3))
    }
  }

  describe("Ex 6") {
    import  misterflibble.fp.chapter3.Ex6._
    it("def init[A](l: List[A]): List[A] = ...") {
      val l5 = List(1,2,3,4,5)
      init(Nil) should equal(Nil)
      init(l5) should equal(List(1,2,3,4))
      init(List(1)) should equal(Nil)
    }
  }

  describe("Ex 7") {
    /* EXERCISE 7: Can product, implemented using foldRight, immediately halt the recursion
     * and return 0.0 if it encounters a 0.0? Why or why not? Consider how any short-circuiting
     * might work if you call foldRight with a large list. This is a deeper question that we’ll
     * return to in chapter 5.
     */

    import  misterflibble.fp.chapter3.Ex7._
    it("implement product on List[Int] with foldRight[Int,Int](as:List[Int], z:Int),(f:(Int,Int)=>Int):Int with a short circuit for 0...?") {
      // don't think it can ... but don't know... ? need to try formulating a reason for that ...
    }
  }

  describe("Ex 8") {
    /* EXERCISE 8: See what happens when you pass Nil and Cons themselves to foldRight, like this:
     * foldRight(List(1,2,3), Nil:List[Int])(Cons(_,_)).10 What do you think this says about the
     * relationship between foldRight and the data constructors of List?
     */
    import  misterflibble.fp.chapter3.Ex7._
    it("pass Nil and Cons themselves to foldRight") {
      foldRight(Nil,42)        ((i:Int, j:Int)=> (throw new Exception("won't happen"))) should equal(42) // identity function on z
      foldRight(Cons(10,Nil),5)((i:Int, j:Int)=> (i*j))                                 should equal(50) // just eval f:(A,B)=>B

      // This relates somehow to dealing with monoids...
      //   Neutral element somehow maps to Nil
      //   Binary function maps to the f:(A,B)=>B parameter   ...?
    }
  }

  describe("Ex 9") {
    it("def length[A](l: List[A]): Int = ??? with foldRight") {
      Ex9.length[Int](Nil)                 should equal(0)
      Ex9.length[Int](Cons(1,Nil))         should equal(1)
      Ex9.length[Int](Cons(2,Cons(1,Nil))) should equal(2)
    }
  }
  describe("Ex 10") {
    import  misterflibble.fp.chapter3.Ex10._
    /** EXERCISE 10: Our implementation of foldRight is not tail-recursive and will
      *     StackOverflow for large lists (we say it’s not stack-safe). Convince yourself
      *     that this is the case, and then write another general list-recursion function,
      *     foldLeft that is tail-recursive, using the techniques we discussed in the
      *     previous chapter. Here is its signature:
      */

    it("implement tail-recursive def foldLeft[A,B](l: List[A], z: B)(f: (B, A) => B): B") {

      Ex10.foldLeft(Nil,0)((i:Int,j:Int)=>i+j)  should equal(0)                   // assert neutral element used
      Ex10.foldLeft(Nil,10)((i:Int,j:Int)=>i+j) should equal(10)

      Ex10.foldLeft(Cons(1,Nil),10)((i:Int,j:Int)=>i+j) should equal(11)          // assert inductive part
      Ex10.foldLeft(Cons(2,Cons(1,Nil)),10)((i:Int,j:Int)=>i+j) should equal(13)
                                                                                  // assert works left to right
      Ex10.foldLeft(List(1,2,3), "*")((s:String, i:Int) => s"($i,$s)") should equal("(3,(2,(1,*)))")
    }
  }

}
