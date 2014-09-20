package nuno.fp.chapter3

import nuno.fp.chapter3.Exercise19.{filter => flFilter}
import nuno.fp.chapter3.Exercise21.{filter => fmFilter}
import org.scalatest.{Matchers, WordSpec}

class Exercise21Spec extends WordSpec with Matchers {
  "Exercise 21" should {
    "conform filter based on foldRight with filter based on flatMap" in {
      fmFilter(Nil) {_ => true} shouldBe flFilter(Nil) {_ => true}
      fmFilter(List(1)) {_ => true} shouldBe flFilter(List(1)) {_ => true}
      fmFilter(List(1)) {_ => false} shouldBe flFilter(List(1)) {_ => false}
      fmFilter(List(1, 2, 3, 4, 5, 6)) {_ % 2 == 0} shouldBe flFilter(List(1, 2, 3, 4, 5, 6)) {_ % 2 == 0}
    }
  }
}
