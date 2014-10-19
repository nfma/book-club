package paul.fp.chapter6

import paul.fp.chapter6.Exercise1.Simple
import paul.fp.chapter6.Exercise2.double
import org.scalatest.{Matchers, WordSpec}

class Exercise2Spec extends WordSpec with Matchers {
  "Exercise 2" should {
    "implement double random number generator" in {
      double (Simple(42)) shouldBe (0.007524831689672932, Simple(1059025964525L))
      double (Simple(1059025964525L)) shouldBe (0.5967354856416283, Simple(197491923327988L))
      double (Simple(1059025964525L)) shouldBe (0.5967354856416283, Simple(197491923327988L))
      double (Simple(197491923327988L)) shouldBe (0.15846728447753344, Simple(259172689157871L))
      double (Simple(259172689157871L)) shouldBe (0.9386595436086224, Simple(149370390209998L))
    }
  }
}
