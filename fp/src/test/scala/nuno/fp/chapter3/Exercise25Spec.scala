package nuno.fp.chapter3

import nuno.fp.chapter3.Exercise25.{size => siz}
import org.scalatest.{Matchers, WordSpec}

class Exercise25Spec extends WordSpec with Matchers {
  "Exercise 25" should {
    "return 1 for the size of a tree with only a leaf" in {
      siz(Leaf(1)) should be(1)
    }

    "return 3 for the size of a tree with a branch with two leaves" in {
      siz(Branch(Leaf(1), Leaf(1))) should be(3)
    }

    "return 5 for the size of a tree with two branches with three leaves" in {
      siz(Branch(Branch(Leaf(1), Leaf(1)), Leaf(1))) should be(5)
      siz(Branch(Leaf(1), Branch(Leaf(1), Leaf(1)))) should be(5)
    }

    "return 7 for the size of a tree with three branches with four leaves" in {
      siz(Branch(Branch(Leaf(1), Leaf(1)), Branch(Leaf(1), Leaf(1)))) should be(7)
    }

    "return 9 for the size of a tree with four branches with five leaves" in {
      siz(Branch(Leaf(1), Branch(Branch(Leaf(1), Leaf(1)), Branch(Leaf(1), Leaf(1))))) should be(9)
      siz(Branch(Branch(Branch(Leaf(1), Leaf(1)), Branch(Leaf(1), Leaf(1))), Leaf(1))) should be(9)
    }
  }
}
