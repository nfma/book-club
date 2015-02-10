package nuno.fp.chapter7

import nuno.fp.chapter7.Exercise10.{Par, run, map}
import nuno.fp.chapter7.Exercise13.chooser

object Exercise14 {
  def join[A](a: Par[Par[A]]): Par[A] = es => run(es)(a)(es)
  def flatMap[A, B](a: Par[A])(f: A => Par[B]): Par[B] = join(map(a)(f))
  def joinFM[A](a: Par[Par[A]]): Par[A] = chooser(a)(identity)
}
