package nuno.fp.chapter7

import java.util.concurrent.{Callable, ExecutorService, Future, TimeUnit}

import scala.concurrent.duration.pairLongToDuration

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
        val d = pairLongToDuration(timeout, units).toNanos
        val elapsed = end - start
        val cb = b.get(d - elapsed, TimeUnit.NANOSECONDS)
        f(ca, cb)
      }

      override def isCancelled = false

      override def cancel(evenIfRunning: Boolean): Boolean = false
    }

    def asyncF[A, B](f: A => B): A => Par[B] = a => Par.lazyUnit(f(a))

    def fork[A](a: => Par[A]): Par[A] = es => es.submit(new Callable[A] {
      def call = a(es).get
    })

    def lazyUnit[A](a: => A): Par[A] = fork(unit(a))

    def map[A, B](pa: Par[A])(f: A => B): Par[B] = map2(pa, unit(()))((a, _) => f(a))

    def map2[A, B, C](a: Par[A], b: Par[B])(f: (A, B) => C): Par[C] = (es: ExecutorService) => TimeoutFuture(a(es), b(es), f)

    def map3[A, B, C, D](a: Par[A], b: Par[B], c: Par[C])(f: (A, B, C) => D): Par[D] =
      a.map2(b.map2(c)((_, _)))((a, b) => f(a, b._1, b._2))

    def map4[A, B, C, D, E](a: Par[A], b: Par[B], c: Par[C], d: Par[D])(f: (A, B, C, D) => E): Par[E] =
      a.map2(b.map2(c.map2(d)((_, _)))((_, _)))((a, b) => f(a, b._1, b._2._1, b._2._2))

    def map5[A, B, C, D, E, F](a: Par[A], b: Par[B], c: Par[C], d: Par[D], e: Par[E])(f: (A, B, C, D, E) => F): Par[F] =
      a.map2(b.map2(c.map2(d.map2(e)((_, _)))((_, _)))((_, _)))((a, b) => f(a, b._1, b._2._1, b._2._2._1, b._2._2._2))

    def sequence[A](ps: List[Par[A]]): Par[List[A]] = ps match {
      case Nil => unit(Nil)
      case head :: tail => head.map2(sequence(tail))(_ :: _)
    }

    def parMap[A, B](ps: List[A])(f: A => B): Par[List[B]] = fork {
      sequence(ps.map(asyncF(f)))
    }

    def parFilter[A](as: List[A])(f: A => Boolean): Par[List[A]] = fork {
      map(sequence(as.map(asyncF {
        case a if f(a) => List(a)
        case _ => Nil
      })))(_.flatten)
    }

    def foldPar[A](s: IndexedSeq[A])(zero: A)(f: (A, A) => A): Par[A] = if (s.size <= 1)
      unit(s.headOption getOrElse zero) else {
      val (l, r) = s.splitAt(s.length / 2)
      foldPar(l)(zero)(f).map2(foldPar(r)(zero)(f))(f)
    }

    def foldPar[A, B](s: List[A])(zero: B)(f: (A, B) => B): Par[B] = {
      s match {
        case Nil => unit(zero)
        case h :: t => unit(h).map2(foldPar(t)(zero)(f))(f)
      }
    }
  }
}
