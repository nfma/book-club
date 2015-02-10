package nuno.fp.chapter7

import nuno.fp.chapter7.Exercise10.{Par, run}
import nuno.fp.chapter7.Exercise13.chooser
import nuno.fp.chapter7.Exercise3.{Par => OldPar}

object Exercise14 {
  def join[A](a: Par[Par[A]]): Par[A] = es => run(es)(a)(es)
  def flatMap[A, B](a: OldPar[A])(f: A => OldPar[B]): OldPar[B] = ??? //join(map(a)(f))
  def joinFM[A](a: Par[Par[A]]): Par[A] = chooser(a)(identity)
}
