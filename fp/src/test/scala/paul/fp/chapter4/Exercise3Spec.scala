package paul.fp.chapter4

import paul.fp.chapter4.Exercise3.map2
import org.scalatest.{Matchers, WordSpec}

class Exercise3Spec extends WordSpec with Matchers {
  "Exercise 3" should {
    "implement map2" in {
//      map2(None, None)(_.toString + _.toString) should be(None)
      map2(Some(1), Some(2))(_.toString + _.toString) should be(Some("12"))
//      map2(Some(1), None)(_.toString + _.toString) should be(None)
//      map2(None, Some(1))(_.toString + _.toString) should be(None)
//      map2(Some("A"), Some(1))(_ + _.toString) should be(Some("A1"))
    }
  }
}
