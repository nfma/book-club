package misterflibble.fp.chapter2

import misterflibble.fp.chapter3._
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, FunSpec}
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
      tail(Cons(1, Cons(2, Cons(3, Nil)))) should equal(Cons(2, Cons(3, Nil)))
      tail(Nil) should equal(Nil)
    }
  }

  describe("Ex 3") {
    import misterflibble.fp.chapter3.Ex3._
    it("setHead") {
      setHead[Int](0, Nil) should equal(Cons(0, Nil))
      setHead[Int](9, Cons(0, Nil)) should equal(Cons(9, Nil))
      setHead[Int](9, Cons(0, Cons(1, Nil))) should equal(Cons(9, Cons(1, Nil)))
    }
  }

  describe("Ex 4") {
    import misterflibble.fp.chapter3.Ex4._
    it("drop n") {
      // scalacheck bait...
      drop[Int](Nil, 0) should equal(Nil)
      drop[Int](Nil, 2) should equal(Nil)

      drop[Int](Cons(1, Nil), 0) should equal(Cons(1, Nil))
      drop[Int](Cons(1, Nil), 1) should equal(Nil)

      drop[Int](Cons(2, Cons(1, Nil)), 0) should equal(Cons(2, Cons(1, Nil)))
      drop[Int](Cons(2, Cons(1, Nil)), 1) should equal(Cons(1, Nil))
      drop[Int](Cons(2, Cons(1, Nil)), 2) should equal(Nil)

      drop[Int](Cons(3, Cons(2, Cons(1, Nil))), 0) should equal(Cons(3, Cons(2, Cons(1, Nil))))
      drop[Int](Cons(3, Cons(2, Cons(1, Nil))), 1) should equal(Cons(2, Cons(1, Nil)))
      drop[Int](Cons(3, Cons(2, Cons(1, Nil))), 2) should equal(Cons(1, Nil))
      drop[Int](Cons(3, Cons(2, Cons(1, Nil))), 3) should equal(Nil)
    }
  }

  describe("Ex 5") {
    import misterflibble.fp.chapter3.Ex5._
    /**
     * See also:  http://www.haskell.org/ghc/docs/6.12.2/html/libraries/base-4.2.0.1/Data-List.html#v%3AdropWhile
     * and:  https://docs.python.org/2/library/itertools.html#itertools.dropwhile
     */
    it("dropWhile") {
      // scalacheck bait...
      def tt(i: Int) = true
      def ff(i: Int) = false
      def odd(i: Int): Boolean = i % 2 == 1
      def even(i: Int): Boolean = !odd(i)
      val l5 = List(1, 2, 3, 4, 5)

      dropWhile(Nil, tt) should equal(Nil)
      dropWhile(Nil, ff) should equal(Nil)
      dropWhile(Nil, odd) should equal(Nil)
      dropWhile(Nil, even) should equal(Nil)

      dropWhile(l5, odd) should equal(List(2, 3, 4, 5))
      dropWhile(l5, even) should equal(l5)
      dropWhile(l5, ff) should equal(l5)
      dropWhile(l5, tt) should equal(Nil)

      dropWhile(List(1, 3, 5, 7, 9, 2, 4, 6, 1, 3), even) should equal(List(1, 3, 5, 7, 9, 2, 4, 6, 1, 3))
      dropWhile(List(1, 3, 5, 7, 9, 2, 4, 6, 1, 3), odd) should equal(List(2, 4, 6, 1, 3))
    }
  }

  describe("Ex 6") {
    import misterflibble.fp.chapter3.Ex6._
    it("def init[A](l: List[A]): List[A] = ...") {
      val l5 = List(1, 2, 3, 4, 5)
      init(Nil) should equal(Nil)
      init(l5) should equal(List(1, 2, 3, 4))
      init(List(1)) should equal(Nil)
    }
  }

  describe("Ex 7") {
    /* EXERCISE 7: Can product, implemented using foldRight, immediately halt the recursion
     * and return 0.0 if it encounters a 0.0? Why or why not? Consider how any short-circuiting
     * might work if you call foldRight with a large list. This is a deeper question that we’ll
     * return to in chapter 5.
     */

    import misterflibble.fp.chapter3.Ex7._
    it("implement product on List[Int] with foldRight[Int,Int](as:List[Int], z:Int),(f:(Int,Int)=>Int):Int with a short circuit for 0...?") {
      // don't think it can ... but don't know... ? need to try formulating a reason for that ...
    }
  }

  describe("Ex 8") {
    /* EXERCISE 8: See what happens when you pass Nil and Cons themselves to foldRight, like this:
     * foldRight(List(1,2,3), Nil:List[Int])(Cons(_,_)).10 What do you think this says about the
     * relationship between foldRight and the data constructors of List?
     */
    import misterflibble.fp.chapter3.Ex7._
    it("pass Nil and Cons themselves to foldRight") {
      foldRight(Nil, 42)((i: Int, j: Int) => (throw new Exception("won't happen"))) should equal(42) // identity function on z
      foldRight(Cons(10, Nil), 5)((i: Int, j: Int) => (i * j)) should equal(50) // just eval f:(A,B)=>B

      // This relates somehow to dealing with monoids...
      //   Neutral element somehow maps to Nil
      //   Binary function maps to the f:(A,B)=>B parameter   ...?
    }
  }

  describe("Ex 9") {
    it("def length[A](l: List[A]): Int = ??? with foldRight") {
      Ex9.length[Int](Nil) should equal(0)
      Ex9.length[Int](Cons(1, Nil)) should equal(1)
      Ex9.length[Int](Cons(2, Cons(1, Nil))) should equal(2)
    }
  }
  describe("Ex 10") {
    import misterflibble.fp.chapter3.Ex10._
    /** EXERCISE 10: Our implementation of foldRight is not tail-recursive and will
      * StackOverflow for large lists (we say it’s not stack-safe). Convince yourself
      * that this is the case, and then write another general list-recursion function,
      * foldLeft that is tail-recursive, using the techniques we discussed in the
      * previous chapter. Here is its signature:
      */

    it("implement tail-recursive def foldLeft[A,B](l: List[A], z: B)(f: (B, A) => B): B") {

      Ex10.foldLeft(Nil, 0)((i: Int, j: Int) => i + j) should equal(0) // assert neutral element used
      Ex10.foldLeft(Nil, 10)((i: Int, j: Int) => i + j) should equal(10)

      Ex10.foldLeft(Cons(1, Nil), 10)((i: Int, j: Int) => i + j) should equal(11) // assert inductive part
      Ex10.foldLeft(Cons(2, Cons(1, Nil)), 10)((i: Int, j: Int) => i + j) should equal(13)
      // assert works left to right
      Ex10.foldLeft(List(1, 2, 3), "*")((s: String, i: Int) => s"($i,$s)") should equal("(3,(2,(1,*)))")
    }
  }

  describe("Ex 11") {
    //EXERCISE 11: Write sum, product, and a function to compute the length of a list using foldLeft.
    it("ex 11 sum") {
      Ex11.sum(List()) should equal(0)
      Ex11.sum(List(1)) should equal(1)
      Ex11.sum(List(1, 2)) should equal(3)
    }
    it("ex 11 product") {
      Ex11.product(List()) should equal(0)
      Ex11.product(List(10)) should equal(10)
      Ex11.product(List(10, 20)) should equal(200)
    }
    it("ex 11 length") {
      Ex11.length(List()) should equal(0)
      Ex11.length(List(10)) should equal(1)
      Ex11.length(List(10, 20)) should equal(2)
    }
  }
  describe("Ex 12") {
    it("ex 12") {
      Ex12.reverse(List()) should equal(List())
      Ex12.reverse(List(1)) should equal(List(1))
      Ex12.reverse(List(1, 2)) should equal(List(2, 1))
      Ex12.reverse(List(1, 2, 3)) should equal(List(3, 2, 1))
    }
  }

  describe("Ex 13") {
    it("ex 13") {
      //      Ex13.foldLeft2()
      //      Ex13.foldRight2()
    }
  }

  describe("Ex 14") {
    it("ex 14 - foldRight") {
      // "could do better..." ;-)
      Ex14.append(List(), List(5, 6, 7, 8)) should equal(List(5, 6, 7, 8))
      Ex14.append(List(1, 2, 3), List()) should equal(List(1, 2, 3))

      Ex14.append(List(1), List(5, 6, 7, 8)) should equal(List(1, 5, 6, 7, 8))
      Ex14.append(List(1, 2), List(5)) should equal(List(1, 2, 5))

      Ex14.append(List(1, 2), List(5, 6, 7, 8)) should equal(List(1, 2, 5, 6, 7, 8))

    }
    //    it("ex 14 - foldLeft"){
    ////      Ex14.appendL(List(),4)  should equal(List(4))
    ////      Ex14.appendL(List(1,2),4)  should equal(List(1,2,4))
    ////      Ex14.appendL(List(1,2,3),4)  should equal(List(1,2,3,4))
    //    }
  }
  describe("Ex 15") {
    it("ex 15 ") {
      Ex15.flatten(List()) should equal(List())
      Ex15.flatten(List(List(1))) should equal(List(1))
      Ex15.flatten(List(List(1), List())) should equal(List(1))
      Ex15.flatten(List(List(1), List(2, 3))) should equal(List(1, 2, 3))
    }
  }
  describe("Ex 16") {
    it("ex 16 - plus oner") {
      Ex16.plusOner(List()) should equal(List())
      Ex16.plusOner(List(0)) should equal(List(1))
      Ex16.plusOner(List(0, 1)) should equal(List(1, 2))
    }
  }
  describe("Ex 17") {
    it("ex 17") {
      Ex17.doublesToStrings(List()) should equal(List())
      Ex17.doublesToStrings(List(0.0)) should equal(List("0.0"))
      Ex17.doublesToStrings(List(0.0, 1.0)) should equal(List("0.0", "1.0"))
    }
  }
  describe("Ex 18") {
    it("ex 18") {
      def plusOne(i: Int) = i + 1
      Ex18.map(List[Int]())(plusOne) should equal(List())
      Ex18.map(List[Int](0))(plusOne) should equal(List(1))
      Ex18.map(List[Int](0, 1))(plusOne) should equal(List(1, 2))

      Ex18.map(List())(_.toString) should equal(List())
      Ex18.map(List(0.0))(_.toString) should equal(List("0.0"))
      Ex18.map(List(0.0, 1.0))(_.toString) should equal(List("0.0", "1.0"))
    }
  }
  describe("Ex 19") {
    it("Ex 19") {
      def odd(i: Int): Boolean = (i % 2) == 1
      def even(i: Int): Boolean = !odd(i)

      Ex19.filter(List())(a => true) should equal(List())
      Ex19.filter(List(0, 1, 2, 3, 4, 5))(a => true) should equal(List(0, 1, 2, 3, 4, 5))
      Ex19.filter(List(0, 1, 2, 3, 4, 5))(a => false) should equal(List())

      Ex19.filter(List(0, 1, 2, 3, 4, 5))(_ > 3) should equal(List(4, 5))
      Ex19.filter(List(0, 1, 2, 3, 4, 5))(odd) should equal(List(1, 3, 5))
      Ex19.filter(List(0, 1, 2, 3, 4, 5))(even) should equal(List(0, 2, 4)) // <- removing all odd numbers
    }
  }

  describe("Ex 20") {
    it("Ex 20 - flatmap") {
      import misterflibble.fp.chapter3.Ex20._

      flatMap(List(1, 2, 3))(i => List(i, i)) should equal(List(1, 1, 2, 2, 3, 3)) // <- test specified in FPS book
      flatMap(List())(i => List(i, i))        should equal(List())
      flatMap(List(1, 2, 3))((i)=>List(i))    should equal(List(1, 2, 3))
    }
  }
  describe("21") {
    it("EXERCISE 21: Can you use flatMap to implement filter?") {
      def odd(i: Int): Boolean = (i % 2) == 1
      def even(i: Int): Boolean = !odd(i)

      Ex21.filter(List())(a => true) should equal(List())
      Ex21.filter(List(0, 1, 2, 3, 4, 5))(a => true) should equal(List(0, 1, 2, 3, 4, 5))
      Ex21.filter(List(0, 1, 2, 3, 4, 5))(a => false) should equal(List())

      Ex21.filter(List(0, 1, 2, 3, 4, 5))(_ > 3) should equal(List(4, 5))
      Ex21.filter(List(0, 1, 2, 3, 4, 5))(odd) should equal(List(1, 3, 5))
      Ex21.filter(List(0, 1, 2, 3, 4, 5))(even) should equal(List(0, 2, 4)) // <- removing all odd numbers

    }
  }
  describe("22") {
    it("EXERCISE 22: Write a function that accepts two lists and constructs a new list\n  by adding corresponding elements.") {
      //For example, List(1,2,3) and List(4,5,6) becomes List(5,7,9).
      //should equal(List())
      Ex22.summer(List(),List())            should equal(List())

      Ex22.summer(List(1),List(2))          should equal(List(3))
      Ex22.summer(List(1,2),List(2,3))      should equal(List(3,5))

      // unequal lengths? choice: treat missing numbers as "zeros"
      Ex22.summer(List(1,2,3),List(8,5))    should equal(List(9,7,3))
      Ex22.summer(List(8,5), List(1,2,3))   should equal(List(9,7,3))

    }
  }

  describe("23") {
    it("Generalize the function you just wrote so that it’s not specific to integers or addition.") {

      def summer2(a1:List[Int],a2:List[Int]) = Ex23.pointwiseBinaryApplier[Int](a1, a2)(_+_)

      summer2(List(),List())            should equal(List())

      summer2(List(1),List(2))          should equal(List(3))
      summer2(List(1,2),List(2,3))      should equal(List(3,5))

      // unequal lengths? choice: treat missing numbers as "zeros"
      summer2(List(1,2,3),List(8,5))    should equal(List(9,7,3))
      summer2(List(8,5), List(1,2,3))   should equal(List(9,7,3))

      Ex23.pointwiseBinaryApplier(List("A","B","C"),List("a","b","c"))((a:String, b:String)=>(s"($a,$b)")) should equal(List("(A,a)","(B,b)","(C,c)"))
      Ex23.pointwiseBinaryApplier(List("A","B"),List("a","b","c"))((a:String, b:String)=>(s"($a,$b)")) should equal(List("(A,a)","(B,b)","c"))
      Ex23.pointwiseBinaryApplier(List("A","B","C"),List("a","b"))((a:String, b:String)=>(s"($a,$b)")) should equal(List("(A,a)","(B,b)","C"))
    }
  }

  describe("24") {
    it("EXERCISE 24 (hard): As an example, implement hasSubsequence for checking whether a List contains another List as a subsequence. " +
      "For instance, List(1,2,3,4) would have List(1,2), List(2,3), and List(4) as subsequences, among others. "){

      Ex24.hasSubsequence(List(), List()) should equal(true) // empty sequence is the only subseq of the empty seq
      Ex24.hasSubsequence(List(), List(1)) should equal(false) // all non-empty sequences are NOT subseq of the empty seq

      Ex24.hasSubsequence(List(1), List(1)) should equal(true) // sequence identical is the trivial subsequence of the sequence

      Ex24.hasSubsequence(List(1), List(1,2)) should equal(false) // all sequences longer than the sequence to be searched -> aren't subsequences

      Ex24.hasSubsequence(List(1,2,3,4,5), List(1,2)) should equal(true) // commencing subsequence

      Ex24.hasSubsequence(List(1,2,3,4,5), List(4,5)) should equal(true) // terminating subsequence

      Ex24.hasSubsequence(List(1,2,3,4,5), List(2,3,4)) should equal(true) // subsequence neither commencing nor terminating
    }
  }

  describe("25") {
    it("EXERCISE 25: Write a function size that counts the number of nodes (leaves and branches) in a tree."){
      Ex25.size(Leaf(1)) should equal(1)
      Ex25.size(Branch(Leaf(1),Leaf(2))) should equal(3)
      Ex25.size(Branch(Leaf(1),Branch(Leaf(10),Leaf(20)))) should equal(5)
      Ex25.size(Branch(Branch(Leaf(10),Leaf(20)),Leaf(1))) should equal(5)
    }
  }

  describe("26") {
    it("Write a function maximum that returns the maximum element in a Tree[Int]."){
      //(Note: in Scala, you can use x.max(y) or x max y to compute the maximum of two integers x and y.)
      Ex26.maximum(Leaf(1)) should equal(1)
      Ex26.maximum(Branch(Leaf(1),Leaf(2))) should equal(2)
      Ex26.maximum(Branch(Leaf(1),Branch(Leaf(10),Leaf(20)))) should equal(20)
      Ex26.maximum(Branch(Leaf(1),Branch(Leaf(20),Leaf(10)))) should equal(20)
      Ex26.maximum(Branch(Branch(Leaf(10),Leaf(20)),Leaf(1))) should equal(20)
      Ex26.maximum(Branch(Branch(Leaf(20),Leaf(10)),Leaf(1))) should equal(20)
    }
  }

  describe("27") {
    it("Write a function depth that returns the maximum path length from the root of a tree to any leaf."){
      Ex27.depth(Leaf(1))                                     should equal(1)
      Ex27.depth(Branch(Leaf(1),Leaf(2)))                     should equal(2)
      Ex27.depth(Branch(Leaf(1),Branch(Leaf(10),Leaf(20))))   should equal(3)
      Ex27.depth(Branch(Leaf(1),Branch(Leaf(20),Leaf(10))))   should equal(3)
      Ex27.depth(Branch(Branch(Leaf(10),Leaf(20)),Leaf(1)))   should equal(3)
      Ex27.depth(Branch(Branch(Leaf(20),Leaf(10)),Leaf(1)))   should equal(3)
    }
  }

  describe("28") {
    it("Write a function map, analogous to the method of the same\nname on List, that modifies each element in a tree with a given function."){
      Ex28.map(Leaf(1))(_.toString)                                     should equal(Leaf("1"))
      Ex28.map(Branch(Leaf(1),Leaf(2)))(_.toString)                     should equal(Branch(Leaf("1"),Leaf("2")))
      Ex28.map(Branch(Leaf(1),Branch(Leaf(10),Leaf(20))))(_.toString)   should equal(Branch(Leaf("1"),Branch(Leaf("10"),Leaf("20"))))
      Ex28.map(Branch(Leaf(1),Branch(Leaf(20),Leaf(10))))(_.toString)   should equal(Branch(Leaf("1"),Branch(Leaf("20"),Leaf("10"))))
      Ex28.map(Branch(Branch(Leaf(10),Leaf(20)),Leaf(1)))(_.toString)   should equal(Branch(Branch(Leaf("10"),Leaf("20")),Leaf("1")))
      Ex28.map(Branch(Branch(Leaf(20),Leaf(10)),Leaf(1)))(_.toString)   should equal(Branch(Branch(Leaf("20"),Leaf("10")),Leaf("1")))
    }
  }
  describe("29") {
    it("Generalize size ") {
      def size2(t: Tree[Int]): Int = Ex29.fold(t)(l=>1)(1+_+_)

      size2(Leaf(1)) should equal(1)
      size2(Branch(Leaf(1), Leaf(2))) should equal(3)
      size2(Branch(Leaf(1), Branch(Leaf(10), Leaf(20)))) should equal(5)
      size2(Branch(Branch(Leaf(10), Leaf(20)), Leaf(1))) should equal(5)
    }

    it("Generalise maximum") {
      def maximum2(t: Tree[Int]): Int = Ex29.fold(t)(i => i)(_ max _)

      maximum2(Leaf(1)) should equal(1)
      maximum2(Branch(Leaf(1), Leaf(2))) should equal(2)
      maximum2(Branch(Leaf(1), Branch(Leaf(10), Leaf(20)))) should equal(20)
      maximum2(Branch(Leaf(1), Branch(Leaf(20), Leaf(10)))) should equal(20)
      maximum2(Branch(Branch(Leaf(10), Leaf(20)), Leaf(1))) should equal(20)
      maximum2(Branch(Branch(Leaf(20), Leaf(10)), Leaf(1))) should equal(20)
    }

    it("Generalise depth") {
      def depth2(t: Tree[Int]): Int = Ex29.fold(t)(i => 1)((a,b)=>(1+(a.max(b))))

      depth2(Leaf(1))                                     should equal(1)
      depth2(Branch(Leaf(1),Leaf(2)))                     should equal(2)
      depth2(Branch(Leaf(1),Branch(Leaf(10),Leaf(20))))   should equal(3)
      depth2(Branch(Leaf(1),Branch(Leaf(20),Leaf(10))))   should equal(3)
      depth2(Branch(Branch(Leaf(10),Leaf(20)),Leaf(1)))   should equal(3)
      depth2(Branch(Branch(Leaf(20),Leaf(10)),Leaf(1)))   should equal(3)
    }

    it("Generalise map") {
      def map2[A,B](t: Tree[A])(f:A=>B): Tree[B] = Ex29.fold(t)(l=>Leaf(f(l)):Tree[B])(Branch[B](_,_))

      map2(Leaf(1))(_.toString)                                     should equal(Leaf("1"))
      map2(Branch(Leaf(1),Leaf(2)))(_.toString)                     should equal(Branch(Leaf("1"),Leaf("2")))
      map2(Branch(Leaf(1),Branch(Leaf(10),Leaf(20))))(_.toString)   should equal(Branch(Leaf("1"),Branch(Leaf("10"),Leaf("20"))))
      map2(Branch(Leaf(1),Branch(Leaf(20),Leaf(10))))(_.toString)   should equal(Branch(Leaf("1"),Branch(Leaf("20"),Leaf("10"))))
      map2(Branch(Branch(Leaf(10),Leaf(20)),Leaf(1)))(_.toString)   should equal(Branch(Branch(Leaf("10"),Leaf("20")),Leaf("1")))
      map2(Branch(Branch(Leaf(20),Leaf(10)),Leaf(1)))(_.toString)   should equal(Branch(Branch(Leaf("20"),Leaf("10")),Leaf("1")))
    }
  }
}