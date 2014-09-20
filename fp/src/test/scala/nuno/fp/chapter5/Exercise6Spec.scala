package nuno.fp.chapter5

import nuno.fp.chapter4.Exercise1.{None, Some}
import nuno.fp.chapter5.Exercise1.Stream
import org.scalatest.{Matchers, WordSpec}

class Exercise6Spec extends WordSpec with Matchers {
  "Exercise 6" should {
    "implement headOption without foldRight" in {
      Stream() headOption() shouldBe None
      Stream(1) headOption() shouldBe Some(1)
      Stream(1, 2, 3) headOption() shouldBe Some(1)
    }

    "implement headOption called headOption2 with foldRight" in {
      Stream() headOption2() shouldBe None
      Stream(1) headOption2() shouldBe Some(1)
      Stream(1, 2, 3) headOption2() shouldBe Some(1)
    }
  }
}
