package nuno.fp.chapter7

import java.util.concurrent.{TimeUnit, Callable, ExecutorService, Future}

import scala.concurrent.duration.TimeUnit

object Exercise3 {

  type Par[A] = ExecutorService => Future[A]

  implicit class Map2Maker[A](a: Par[A]) {
    def map2[B, C](b: Par[B]): ((A, B) => C) => Par[C] = f => Par.map2[A, B, C](a, b)(f)
  }

  object Par {

    def unit[A](a: A): Par[A] = (es: ExecutorService) => UnitFuture(a)

    private case class UnitFuture[A](get: A) extends Future[A] {
      override def isDone = true

      override def get(timeout: Long, units: TimeUnit): A = get

      override def isCancelled = false

      override def cancel(evenIfRunning: Boolean): Boolean = false
    }

    private case class TimeoutFuture[A, B, C](a: Future[A], b: Future[B], f: (A, B) => C) extends Future[C] {
      override def isDone = true

      override def get: C = f(a.get, b.get)

      override def get(timeout: Long, units: TimeUnit): C = {
        val start = System.nanoTime()
        val ca = a.get(timeout, units)
        val end = System.nanoTime()
        val d = scala.concurrent.duration.pairLongToDuration(timeout, units).toNanos
        val elapsed = end - start
        val cb = b.get(d - elapsed, TimeUnit.NANOSECONDS)
        f(ca, cb)
      }

      override def isCancelled = false

      override def cancel(evenIfRunning: Boolean): Boolean = false
    }

    def map2[A, B, C](a: Par[A], b: Par[B])(f: (A, B) => C): Par[C] = (es: ExecutorService) => TimeoutFuture(a(es), b(es), f)

    def fork[A](a: => Par[A]): Par[A] = es => es.submit(new Callable[A] {
      def call = a(es).get
    })

    def lazyUnit[A](a: => A): Par[A] = fork(unit(a))
  }

}
