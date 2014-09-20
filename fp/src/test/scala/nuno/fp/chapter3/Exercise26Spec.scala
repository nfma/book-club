package nuno.fp.chapter3

import nuno.fp.chapter3.Exercise26.maximum
import org.scalatest.{Matchers, WordSpec}

class Exercise26Spec extends WordSpec with Matchers {
  "Exercise 26" should {
    "return the leaf value as the maximum of a single leaf tree" in {
      maximum(Leaf(1)) shouldBe 1
    }

    "return the maximum of the two leaves as the maximum of a tree with a branch and two leaves" in {
      maximum(Branch(Leaf(1), Leaf(2))) shouldBe 2
      maximum(Branch(Leaf(2), Leaf(1))) shouldBe 2
      maximum(Branch(Leaf(1), Leaf(1))) shouldBe 1
    }

    "return 3 as the maximum a tree with three leaves with a tree with two branches and three leaves" in {
      maximum(Branch(Branch(Leaf(1), Leaf(2)), Leaf(3))) shouldBe 3
      maximum(Branch(Leaf(1), Branch(Leaf(2), Leaf(3)))) shouldBe 3
    }

    "return 4 as the maximum a tree with three leaves with a tree with three branches and four leaves" in {
      maximum(Branch(Branch(Leaf(1), Leaf(2)), Branch(Leaf(3), Leaf(4)))) shouldBe 4
      maximum(Branch(Branch(Leaf(1), Leaf(4)), Branch(Leaf(3), Leaf(2)))) shouldBe 4
      maximum(Branch(Branch(Leaf(4), Leaf(1)), Branch(Leaf(3), Leaf(2)))) shouldBe 4
      maximum(Branch(Branch(Leaf(1), Leaf(3)), Branch(Leaf(2), Leaf(4)))) shouldBe 4
      maximum(Branch(Branch(Leaf(3), Leaf(1)), Branch(Leaf(4), Leaf(2)))) shouldBe 4
    }
  }
}
