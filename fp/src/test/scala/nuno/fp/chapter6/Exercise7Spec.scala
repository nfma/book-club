package nuno.fp.chapter6

import nuno.fp.chapter6.Exercise1.{Simple, nonNegativeInt}
import nuno.fp.chapter6.Exercise5.double
import nuno.fp.chapter6.Exercise7.{ints, sequence}
import org.scalatest.{Matchers, WordSpec}

class Exercise7Spec extends WordSpec with Matchers {
  "Exercise 7" should {
    "implement sequence" in {
      sequence(List())(Simple(42)) should be((Nil, Simple(42)))
      sequence[Double](List(rng => double(rng)))(Simple(42)) should be((List(0.007524831689672932), Simple(1059025964525L)))
      sequence(List(rng => nonNegativeInt(rng), rng => double(rng), rng => double(rng)))(Simple(42)) should
        be((List(16159453, 0.5967354856416283, 0.15846728447753344), Simple(259172689157871L)))
    }

    "implement ints using sequence" in {
      ints(0)(Simple(42)) should be(Nil, Simple(42))
      ints(1)(Simple(42)) should be(List(16159453), Simple(1059025964525L))
      ints(2)(Simple(42)) should be(List(16159453, -1281479697), Simple(197491923327988L))
      ints(10)(Simple(42)) should be(List(16159453, -1281479697, -340305902, -2015756020, 1770001318, -1934589059,
        1015914512, -1163632441, -94901159, 1837487774), Simple(120421598792892L))
    }
  }
}
