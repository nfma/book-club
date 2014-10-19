package paul.fp.chapter6

import paul.fp.chapter6.Exercise1.RNG

object Exercise4 {
  def ints(count: Int)(rng: RNG): (List[Int], RNG) = (count, rng) match {
    case (0, gen) => (Nil, rng)
    case (num, gen) =>
      val (n, gen2) = gen.nextInt
      val (l, gen3) = ints(num-1)(gen2)
      (n::l, gen3)
  }

}
