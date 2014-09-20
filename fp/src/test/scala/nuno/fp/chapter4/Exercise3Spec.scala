package nuno.fp.chapter4

import nuno.fp.chapter4.Exercise1.{None, Some}
import nuno.fp.chapter4.Exercise3.map2
import org.scalatest.{Matchers, WordSpec}

class Exercise3Spec extends WordSpec with Matchers {
  "Exercise 3" should {
    "implement map2" in {
      map2[Int, Int, Int](None, None) {_ + _} shouldBe None
      map2(Some(1), None) {_ + _} shouldBe None
      // interesting that the first parameter is able to help the compiler infer the type but not the second
      map2[Int, Int, Int](None, Some(1)) {_ + _} shouldBe None
      map2(Some(1), Some(1)) {_ + _} shouldBe Some(2)
    }
  }
}
