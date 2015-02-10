package nuno.fp.chapter7

import nuno.fp.chapter7.Exercise10.{run, unit, Par}

object Exercise11 {
  def choiceN[A](n: Par[Int])(choices: List[Par[A]]): Par[A] = es => choices(run(es)(n))(es)
  def choice[A](b: Par[Boolean])(t: Par[A], f: Par[A]): Par[A] = choiceN(es => if(run(es)(b)) unit(0)(es) else unit(1)(es))(List(t, f))
}
