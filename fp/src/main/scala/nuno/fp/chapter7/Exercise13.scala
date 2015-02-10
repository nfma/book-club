package nuno.fp.chapter7

import nuno.fp.chapter7.Exercise10.{Par, run}

object Exercise13 {
  def chooser[A,B](pa: Par[A])(choices: A => Par[B]): Par[B] = es => choices(run(es)(pa))(es)
  def choice[A](b: Par[Boolean])(t: Par[A], f: Par[A]): Par[A] = chooser(b)(b => if(b) t else f)
  def choiceN[A](n: Par[Int])(choices: List[Par[A]]): Par[A] = chooser(n)(n => choices(n))
}
