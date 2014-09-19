package nuno.fp.chapter5

import nuno.fp.chapter4.Exercise1.Some
import nuno.fp.chapter5.Exercise1.Stream
import nuno.fp.chapter5.Exercise1.Stream.unfold


object Exercise12 {
  def constant[A](a: A): Stream[A] = unfold(0) {s => Some((a, s))}

  def from(n: Int): Stream[Int] = unfold(0) {s => Some((n + s, s + 1))}

  def fibs: Stream[Int] = unfold((0, 1)) {s => Some((s._1, (s._2, s._1 + s._2)))}

  def ones: Stream[Int] = unfold(0) {s => Some((1, s))}
}
