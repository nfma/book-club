package nuno.fp.chapter3

import nuno.fp.chapter3.Exercise27.depth
import org.scalatest.{Matchers, WordSpec}

class Exercise27Spec extends WordSpec with Matchers {
  "Exercise 27" should {
    "return 1 for the depth of a leaf" in {
      depth(Leaf(1)) shouldBe 1
    }

    "return 2 for the depth of a tree with a branch and two leaves" in {
      depth(Branch(Leaf(1), Leaf(1))) shouldBe 2
    }

    "return 3 for the depth of a tree with two branches and three leaves" in {
      depth(Branch(Branch(Leaf(1), Leaf(1)), Leaf(1))) shouldBe 3
      depth(Branch(Leaf(1), Branch(Leaf(1), Leaf(1)))) shouldBe 3
    }

    "return 4 for the depth of an unbalanced tree with three branches and four leaves" in {
      depth(Branch(Branch(Branch(Leaf(1), Leaf(1)), Leaf(1)), Leaf(1))) shouldBe 4
      depth(Branch(Branch(Leaf(1), Branch(Leaf(1), Leaf(1))), Leaf(1))) shouldBe 4
      depth(Branch(Leaf(1), Branch(Branch(Leaf(1), Leaf(1)), Leaf(1)))) shouldBe 4
      depth(Branch(Leaf(1), Branch(Leaf(1), Branch(Leaf(1), Leaf(1))))) shouldBe 4
    }
  }
}
