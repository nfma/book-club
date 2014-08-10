package nuno.fp.chapter3

import nuno.fp.chapter3.Exercise4._
import org.scalatest.{Matchers, WordSpec}

class Exercise4Spec extends WordSpec with Matchers {
//  Due to our inefficient list we can't run this with scalacheck, it's too sloooooooooow :(
//
//  property("A list that drops zero or negative elements maintains the same size") =
//    forAll {
//      (l: scala.collection.immutable.List[Int], n: Int) => (n <= 0) ==> (drop(List(l:_*), n) == List(l:_*))
//    }
//
//  property("An empty list always stays empty regardless of the number of drops") =
//    forAll {
//      (n: Int) => drop(Nil, n) == Nil
//    }
//
//  property("A list that drops X elements has at most the original length minus the number of dropped elements") =
//    forAll {
//      (l: scala.collection.immutable.List[Int], n: Int) => (n > 0) ==> (length(drop(List(l:_*), n)) >= length(List(l:_*)) - n)
//    }

  "Exercise 4" should {
    "return the original list when dropping zero or negative number of elements " in {
      drop(List(1), 0) should be(List(1))
      drop(List(1), -1) should be(List(1))
      drop(List(1), -23) should be(List(1))
    }

    "return an empty list when dropping any number of elements of an empty list" in {
      drop(Nil, 1) should be(Nil)
      drop(Nil, 435) should be(Nil)
    }

    "return an empty list when dropping any positive number of elements of single element list" in {
      drop(List(1), 1) should be(Nil)
      drop(List(1), 353) should be(Nil)
    }

    "return a list with X less elements when dropping X from a list" in {
      drop(List(1, 2, 3), 2) should be(List(3))
      drop(List(1, 2, 3, 4, 5), 2) should be(List(3, 4, 5))
    }
  }
}
