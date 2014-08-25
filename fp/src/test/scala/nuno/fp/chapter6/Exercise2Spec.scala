package nuno.fp.chapter6

import nuno.fp.chapter6.Exercise1.Simple
import nuno.fp.chapter6.Exercise2.double
import org.scalatest.{Matchers, WordSpec}

class Exercise2Spec extends WordSpec with Matchers {
  "Exercise 2" should {
    "implement double random number generator" in {
      double(Simple(42)) should be((0.007524831689672932, Simple(1059025964525L)))
      double(Simple(1059025964525L)) should be((0.5967354856416283, Simple(197491923327988L)))
      double(Simple(1059025964525L)) should be((0.5967354856416283, Simple(197491923327988L)))
      double(Simple(197491923327988L)) should be((0.15846728447753344, Simple(259172689157871L)))
      double(Simple(259172689157871L)) should be((0.9386595436086224, Simple(149370390209998L)))
    }
  }
}
