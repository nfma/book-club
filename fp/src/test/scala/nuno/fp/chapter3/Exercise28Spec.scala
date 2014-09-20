package nuno.fp.chapter3

import nuno.fp.chapter3.Exercise28.map
import org.scalatest.{Matchers, WordSpec}

class Exercise28Spec extends WordSpec with Matchers {
  "Exercise 28" should {
    "return a leaf when mapping over a single leaf" in {
      map(Leaf(1)) {_ + 1} shouldBe Leaf(2)
      map(Leaf(1)) {identity} shouldBe Leaf(1)
      map(Leaf(1)) {_.toString} shouldBe Leaf("1")
    }

    "return a tree with the same structure only with the values changed when mapping over the tree" in {
      map(Branch(Leaf(1), Leaf(1))) {identity} shouldBe Branch(Leaf(1), Leaf(1))
      map(Branch(Leaf(1), Branch(Leaf(1), Leaf(1)))) {identity} shouldBe Branch(Leaf(1), Branch(Leaf(1), Leaf(1)))
      map(Branch(Branch(Leaf(1), Leaf(1)), Branch(Leaf(1), Leaf(1)))) {identity} shouldBe Branch(Branch(Leaf(1), Leaf(1)), Branch(Leaf(1), Leaf(1)))
    }
  }
}
