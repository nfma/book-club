package nuno.fp.chapter4

import nuno.fp.chapter4.Exercise1.{None, Some}
import nuno.fp.chapter4.Exercise2.variance
import org.scalatest.{Matchers, WordSpec}

class Exercise2Spec extends WordSpec with Matchers {
  "Exercise 2" should {
    "calculate variance" in {
      variance(Seq()) should be(None)
      variance(Seq(1.0)) should be(Some(0.0))
      variance(Seq(1.0, 2.0)) should be(Some(0.25))
    }
  }
}
