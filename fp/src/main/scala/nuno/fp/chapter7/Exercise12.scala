package nuno.fp.chapter7

import nuno.fp.chapter7.Exercise10.{Par, run}

object Exercise12 {
  def choiceMap[K,V](key: Par[K])(choices: Map[K,Par[V]]): Par[V] = es => choices(run(es)(key))(es)
}
