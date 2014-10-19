package paul.fp.chapter6

import paul.fp.chapter6.Exercise1.Simple
import paul.fp.chapter6.Exercise4.ints
import org.scalatest.{Matchers, WordSpec}

class Exercise4Spec extends WordSpec with Matchers {
  "Exercise 4" should {
    "implement ints" in {
      ints(0) {Simple(42)} shouldBe (Nil, Simple(42))
      ints(1) {Simple(42)} shouldBe (List(16159453), Simple(1059025964525L))
      ints(2) {Simple(42)} shouldBe (List(16159453, -1281479697), Simple(197491923327988L))
      ints(10) {Simple(42)} shouldBe (List(16159453, -1281479697, -340305902, -2015756020, 1770001318, -1934589059,
        1015914512, -1163632441, -94901159, 1837487774), Simple(120421598792892L))
    }
  }

}
