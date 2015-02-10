package nuno.fp.chapter7

object Exercise9 {
  println(
    """taking the example given:
      |val a = lazyUnit(42 + 1)
      |val S = Executors.newFixedThreadPool(1)
      |println(Par.equal(S)(a, fork(a)))
      |
      |and that fork(fork(a)) should be the same as a
      |then as long as forks spawn new forks, then the number of chained forks
      |can always be made to be higher than the number of threads available.
      |
    """.stripMargin)
}
