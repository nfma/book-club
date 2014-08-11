package nuno.fp.chapter3

import nuno.fp.chapter3.Exercise25.{size => siz}
import nuno.fp.chapter3.Exercise26.maximum
import nuno.fp.chapter3.Exercise27.depth
import nuno.fp.chapter3.Exercise28.map
import nuno.fp.chapter3.Exercise29.fold
import org.scalatest.{Matchers, WordSpec}

class Exercise29Spec extends WordSpec with Matchers {
  "Exercise 29" should {
    "generalise size" in {
      fold[String, Int](Leaf("1"))(_ => 1, _ + _ + 1) should be(siz(Leaf("1")))
      fold[String, Int](Branch(Leaf("1"), Leaf("1")))(_ => 1, _ + _ + 1) should be(siz(Branch(Leaf("1"), Leaf("1"))))
      fold[String, Int](Branch(Branch(Leaf("1"), Leaf("1")), Leaf("1")))(_ => 1, _ + _ + 1) should be(siz(Branch(Branch(Leaf("1"), Leaf("1")), Leaf("1"))))
      fold[String, Int](Branch(Leaf("1"), Branch(Leaf("1"), Leaf("1"))))(_ => 1, _ + _ + 1) should be(siz(Branch(Leaf("1"), Branch(Leaf("1"), Leaf("1")))))
      fold[String, Int](Branch(Branch(Leaf("1"), Leaf("1")), Branch(Leaf("1"), Leaf("1"))))(_ => 1, _ + _ + 1) should be(siz(Branch(Leaf("1"), Branch(Leaf("1"), Branch(Leaf("1"), Leaf("1"))))))
    }

    "generalise maximum" in {
      fold[Int, Int](Leaf(1))(identity, _ max _) should be(maximum(Leaf(1)))
      fold[Int, Int](Branch(Leaf(1), Leaf(1)))(identity, _ max _) should be(maximum(Branch(Leaf(1), Leaf(1))))
      fold[Int, Int](Branch(Branch(Leaf(1), Leaf(1)), Leaf(1)))(identity, _ max _) should be(maximum(Branch(Branch(Leaf(1), Leaf(1)), Leaf(1))))
      fold[Int, Int](Branch(Leaf(1), Branch(Leaf(1), Leaf(1))))(identity, _ max _) should be(maximum(Branch(Leaf(1), Branch(Leaf(1), Leaf(1)))))
      fold[Int, Int](Branch(Branch(Leaf(1), Leaf(1)), Branch(Leaf(1), Leaf(1))))(identity, _ max _) should be(maximum(Branch(Branch(Leaf(1), Leaf(1)), Branch(Leaf(1), Leaf(1)))))
    }

    "generalise depth" in {
      fold[Int, Int](Leaf(1))(_ => 1, (dl, dr) => (dl max dr) + 1) should be(depth(Leaf(1)))
      fold[Int, Int](Branch(Leaf(1), Leaf(1)))(_ => 1, (dl, dr) => (dl max dr) + 1) should be(depth(Branch(Leaf(1), Leaf(1))))
      fold[Int, Int](Branch(Branch(Leaf(1), Leaf(1)), Leaf(1)))(_ => 1, (dl, dr) => (dl max dr) + 1) should be(depth(Branch(Branch(Leaf(1), Leaf(1)), Leaf(1))))
      fold[Int, Int](Branch(Leaf(1), Branch(Leaf(1), Leaf(1))))(_ => 1, (dl, dr) => (dl max dr) + 1) should be(depth(Branch(Leaf(1), Branch(Leaf(1), Leaf(1)))))
      fold[Int, Int](Branch(Branch(Leaf(1), Leaf(1)), Branch(Leaf(1), Leaf(1))))(_ => 1, (dl, dr) => (dl max dr) + 1) should be(depth(Branch(Branch(Leaf(1), Leaf(1)), Branch(Leaf(1), Leaf(1)))))
    }

    "generalise map" in {
      fold[Int, Tree[Int]](Leaf(1))(Leaf(_), (l, r) => Branch(l, r)) should be(map(Leaf(1))(identity))
      fold[Int, Tree[Int]](Branch(Leaf(1), Leaf(1)))(Leaf(_), (l, r) => Branch(l, r)) should be(map(Branch(Leaf(1), Leaf(1)))(identity))
      fold[Int, Tree[Int]](Branch(Branch(Leaf(1), Leaf(1)), Leaf(1)))(Leaf(_), (l, r) => Branch(l, r)) should be(map(Branch(Branch(Leaf(1), Leaf(1)), Leaf(1)))(identity))
      fold[Int, Tree[Int]](Branch(Leaf(1), Branch(Leaf(1), Leaf(1))))(Leaf(_), (l, r) => Branch(l, r)) should be(map(Branch(Leaf(1), Branch(Leaf(1), Leaf(1))))(identity))
      fold[Int, Tree[Int]](Branch(Branch(Leaf(1), Leaf(1)), Branch(Leaf(1), Leaf(1))))(Leaf(_), (l, r) => Branch(l, r)) should be(map(Branch(Branch(Leaf(1), Leaf(1)), Branch(Leaf(1), Leaf(1))))(identity))
    }
  }
}
