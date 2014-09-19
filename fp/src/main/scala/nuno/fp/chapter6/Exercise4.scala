package nuno.fp.chapter6

import nuno.fp.chapter6.Exercise1.RNG

object Exercise4 {
  def ints(count: Int)(rng: RNG): (List[Int], RNG) = (count, rng) match {
    case (0, gen) => (Nil, gen)
    case (nr, gen) =>
      val (a, gen2) = gen.nextInt
      val (l, gen3) = ints(nr - 1) {gen2}
      (a :: l, gen3)
  }
}
