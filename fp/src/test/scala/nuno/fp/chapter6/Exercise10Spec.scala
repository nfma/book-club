package nuno.fp.chapter6

import nuno.fp.chapter6.Exercise1.{RNG, Simple, nonNegativeInt}
import nuno.fp.chapter6.Exercise10.State
import nuno.fp.chapter6.Exercise10.State.{sequence, unit}
import nuno.fp.chapter6.Exercise5.double
import org.scalatest.{Matchers, WordSpec}

class Exercise10Spec extends WordSpec with Matchers {
  "Exercise 10" should {
    "implement State.unit" in {
      State.unit(1).run(Simple(42)) should be((1, Simple(42)))
    }

    "implement State.map" in {
      unit(1).map(identity).run(Simple(42)) should be((1, Simple(42)))
      unit(1).map(_ + 1).run(Simple(42)) should be((2, Simple(42)))

      def double(rng: RNG): (Double, RNG) = State(nonNegativeInt).map(_.toDouble / Int.MaxValue.toDouble).run(rng)

      double(Simple(42)) should be((0.007524831689672932, Simple(1059025964525L)))
      double(Simple(1059025964525L)) should be((0.5967354856416283, Simple(197491923327988L)))
      double(Simple(1059025964525L)) should be((0.5967354856416283, Simple(197491923327988L)))
      double(Simple(197491923327988L)) should be((0.15846728447753344, Simple(259172689157871L)))
      double(Simple(259172689157871L)) should be((0.9386595436086224, Simple(149370390209998L)))
    }

    "implement State.map2" in {
      State(nonNegativeInt).map2(State(nonNegativeInt))((_, _)).run(Simple(42)) should be((16159453, 1281479697), Simple(197491923327988L))
    }

    "implement State.flatMap" in {
      State(nonNegativeInt).flatMap(_ => State(nonNegativeInt)).run(Simple(42)) should be((1281479697, Simple(197491923327988L)))
      State(nonNegativeInt).flatMap(a => State(rng => {
        val (b, rng2) = rng.nextInt
        (b + a, rng2)
      })).run(Simple(42)) should be((-1265320244, Simple(197491923327988L)))
    }

    "implement State.sequence" in {
      sequence(List()).run(Simple(42)) should be((Nil, Simple(42)))
      sequence(List(State(double))).run(Simple(42)) should be((List(0.007524831689672932), Simple(1059025964525L)))
      sequence(List(State(nonNegativeInt), State(double), State(double))).run(Simple(42)) should
        be((List(16159453, 0.5967354856416283, 0.15846728447753344), Simple(259172689157871L)))

    }
  }
}
