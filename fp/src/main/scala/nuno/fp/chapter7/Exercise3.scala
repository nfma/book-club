package nuno.fp.chapter7

import java.util.concurrent.{Callable, ExecutorService, Future}

import scala.concurrent.duration.TimeUnit

object Exercise3 {
  type Par[A] = ExecutorService => Future[A]

  object Par {

    def unit[A](a: A): Par[A] = (es: ExecutorService) => UnitFuture(a)

    private case class UnitFuture[A](get: A) extends Future[A] {
      override def isDone = true

      override def get(timeout: Long, units: TimeUnit) = get

      override def isCancelled = false

      override def cancel(evenIfRunning: Boolean): Boolean = false
    }

    def map2[A, B, C](a: Par[A], b: Par[B])(f: (A, B) => C): Par[C] = (es: ExecutorService) => {
      val af = a(es)
      val bf = b(es)
      UnitFuture(f(af.get, bf.get))
    }

    def fork[A](a: => Par[A]): Par[A] = es => es.submit(new Callable[A] {
      def call = a(es).get
    })
  }

}
