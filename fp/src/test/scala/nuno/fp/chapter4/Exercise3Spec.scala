package nuno.fp.chapter4

import nuno.fp.chapter4.Exercise1.{None, Some}
import nuno.fp.chapter4.Exercise3.map2
import org.scalatest.{Matchers, WordSpec}

class Exercise3Spec extends WordSpec with Matchers {
  "Exercise 3" should {
    "implement map2" in {
      map2(None, None)(_.toString + _.toString) should be(None)
      map2(Some(1), None)(_.toString + _.toString) should be(None)
      map2(None, Some(1))(_.toString + _.toString) should be(None)
      map2(Some(1), Some(1))(_.toString + _.toString) should be(Some("11"))
    }
  }
}
