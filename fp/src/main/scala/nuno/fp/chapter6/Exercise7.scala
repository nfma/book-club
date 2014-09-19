package nuno.fp.chapter6

import nuno.fp.chapter6.Exercise1.RNG
import nuno.fp.chapter6.Exercise5.Rand
import nuno.fp.chapter6.Exercise6.map2

object Exercise7 {
  def sequence[A](fs: List[Rand[A]]): Rand[List[A]] = fs match {
    case Nil => rnd => (Nil, rnd)
    case h :: t => map2(h, sequence(t)) {_ :: _}
  }

  def ints(count: Int)(rng: RNG): (List[Int], RNG) = sequence(List.fill[Rand[Int]](count) {_.nextInt}) {rng}
}
