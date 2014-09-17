package ben.fp.chapter3

import ben.fp.chapter3.Tree._
import ben.fp.chapter3.List._
import org.scalatest.{Matchers, WordSpec}
import scala.annotation.tailrec

class Chapter3Spec extends WordSpec with Matchers {


  "Exercise 2 - tail" in {

    tail(List(3)) shouldBe Nil
    tail(List(2, 3)) shouldBe List(3)
    tail(List(1, 2, 3)) shouldBe List(2, 3)
  }

  "Exercise 3 - setHead" in {

    setHead(5, Nil) shouldBe List(5)
    setHead(3, List(4)) shouldBe List(3)
    setHead(3, List(1, 4)) shouldBe List(3, 4)
  }

  "Exercise 3 - drop" in {

    drop(Nil, 1) shouldBe Nil
    drop(Nil, 0) shouldBe Nil
    drop(List(1), 0) shouldBe List(1)
    drop(List(1), 1) shouldBe Nil
    drop(List(1, 2), 1) shouldBe List(2)
    drop(List(1, 2, 3), 1) shouldBe List(2, 3)
    drop(List(1, 2, 3, 4), 2) shouldBe List(3, 4)
  }

  "Exercise 4 - tail with drop" in {

    tail(List(3)) shouldBe Nil
    tail(List(2, 3)) shouldBe List(3)
    tail(List(1, 2, 3)) shouldBe List(2, 3)
  }

  "Exercise 5 - dropWhile" in {

    dropWhile[Int](Nil)( n => n > 2) shouldBe Nil
    dropWhile(List(3, 4, 5))( n => n > 2) shouldBe Nil
    dropWhile(List(1))( n => n > 2) shouldBe List(1)
    dropWhile(List(2, 1))( n => n > 2) shouldBe List(2, 1)
    dropWhile(List(3, 2, 1))( n => n > 2) shouldBe List(2, 1)
  }


  // note: cannot be implemented in constant time O(1), it is always linear time O(n)
  // the reason is that every element in the list must be traversed to get to the end
  "Exercise 6 - init" in {

    init(Nil) shouldBe Nil
    init(List(1)) shouldBe Nil
    init(List(1,2)) shouldBe List(1)
    init(List(1,2,3)) shouldBe List(1,2)
  }


  "Exercise 7 - init" in {

    // no code, foldRight cannot terminate early
  }

  "Exercise 8 - foldRight" in {
// note:
   foldRight(List(1,2,3), Nil:List[Int])(Cons(_,_)) shouldBe Cons(1,Cons(2,Cons(3,Nil)))
   foldRight(List(1,2,3), Nil:List[Int])(Cons(_,_)) shouldBe List(1,2,3)

  }

  "Exercise 9 - length using foldRighwt" in {

    List.length(Nil:List[Int]) shouldBe 0

    List.length(List()) shouldBe 0
    List.length(List(1)) shouldBe 1
    List.length(List(1,2)) shouldBe 2
    List.length(List(1,2, 3)) shouldBe 3
  }

  "Exercise 10 - foldRight is not stack safe, foldLeft is" in {

    @tailrec
    def buildList(list:List[Int], count:Int=0) :List[Int]= {
      if(count == 100000) list
      else buildList(Cons(count, list), count +1)
    }

    val veryBigList = buildList(Nil)

    // Yarp...
    intercept[StackOverflowError]{
      foldRight(veryBigList, 0:Int)(_ + _)
    }

    foldLeft(List(1,2,3), 0)(_ + _) shouldBe 6
    foldLeft(veryBigList, 0)(_ + _) shouldBe 704982704
  }

  // Exercise 11 - upadated implementation of sum, product and length using fold left

  "Exercise 12 - reverse using  fold" in {

    reverse(List(1,2,3,4)) shouldBe List(4,3,2,1)
  }

  "Exercise 13 - foldLeft using foldRight" in {

    foldRightUsingFoldLeft(List("h","e","l","l","o"), "")(_ + _) shouldBe foldLeft(List("h", "e", "l", "l", "o"), "")(_ + _)
    foldLeftUsingFoldRight(List("h","e","l","l","o"), "")(_ + _) shouldBe foldRight(List("h", "e", "l", "l", "o"), "")(_ + _)
  }

  "Exercise 14 - append" in {

    append(List("a"), List("b","c","d","e")) shouldBe List("a","b","c","d","e")
  }

  "Exercise 15 - concatenate" in {

    concatenate(List(List("a"), List("b","c"), List("d","e","f"))) shouldBe List("a","b","c","d","e","f")
  }

  "Exercise 16 - addOne " in {

    addOne(List(1,2,3,4,5,6,7,8,9)) shouldBe List(2,3,4,5,6,7,8,9,10)
  }

  "Exercise 17 - stringify " in {

    stringify(List(1,2,3)) shouldBe "1.02.03.0"
  }

  "Exercise 18 - map " in {

    List.map(List(1,2,3))(_ + 1) shouldBe List(2,3,4)
    List.map(List(2,3,4))(_.toString) shouldBe List("2","3","4")
  }

  "Exercise 19 - filter " in {

    filter(List(1,2,3,4))(_ % 2 == 0) shouldBe List(2,4)
  }

  "Exercise 20 - flatMap " in {

    flatMap(List(1,2,3,4))(i => List(i,i)) shouldBe List(1,1,2,2,3,3,4,4)
  }


  "Exercise 21 - filter using flatMap " in {

    filter2(List(1,2,3,4))(_ % 2 == 0) shouldBe List(2,4)
  }


  "Exercise 22 - zipWith" in {

    zipWith(List(1,2,3), List(4,5,6))(_ + _) shouldBe List(5,7,9)
  }


  "Exercise 23 - generalised zipWith" in {

    zipWith(List("1","2","3"), List("4","5","6"))(_ + _) shouldBe List("14","25","36")
  }

  "Exercise 24 - has subsequence" in {

    hasSubsequence(List("1","2","3","4","5"), List("4","5")) shouldBe true
    hasSubsequence(List("1","2","3","4","5"), List("1")) shouldBe true
    hasSubsequence(List("1","2","3","4","5"), List("1","2","3","4","5")) shouldBe true
    hasSubsequence(List("1","2","3","4","5"), List("1","2","3")) shouldBe true
  }




  "Exercise 25 - tree size" in {

    Tree.size(Branch(Leaf("a"), Leaf("b"))) shouldBe 3
    Tree.size(Branch(Branch(Leaf("a"), Leaf("b")), Leaf("b"))) shouldBe 5
    Tree.size(
      Branch(
        Branch(Leaf("a"), Leaf("b")),
        Branch(Leaf("a"),
          Branch(Leaf("a"), Leaf("b"))
        )
      )
    ) shouldBe 9
  }

  "Exercise 26 - tree maximum" in {

    Tree.maximum[Int](Branch(Leaf(1), Leaf(2))) shouldBe 2
    Tree.maximum[Int](Branch(Branch(Leaf(1), Leaf(2)), Leaf(3))) shouldBe 3
  }

  "Exercise 27 - tree depth" in {

    Tree.depth(Branch(Leaf(1), Leaf(2))) shouldBe 2
    Tree.depth(Branch(Branch(Leaf(1), Leaf(2)), Leaf(3))) shouldBe 3
    Tree.depth(Branch(Branch(Branch(Leaf(1), Leaf(1)), Leaf(1)), Leaf(1))) shouldBe 4
  }

  "Exercise 28 - tree map" in {

    Tree.map(Branch(Leaf(1), Leaf(2)))(_ + 1) shouldBe Branch(Leaf(2), Leaf(3))
  }
}
