package nuno.fp.chapter7

import java.util.concurrent.atomic.AtomicReference
import java.util.concurrent.{Callable, CountDownLatch, ExecutorService}

object Exercise10 {

  sealed trait Future[A] {
    private[Exercise10] def apply(k: A => Unit)(c: Exception => Unit): Unit
  }

  type Par[A] = ExecutorService => Future[A]

  def run[A](es: ExecutorService)(p: Par[A]): A = {
    val ref = new AtomicReference[A]
    val exRef = new AtomicReference[Exception]
    val latch = new CountDownLatch(1)
    p(es){a => ref.set(a); latch.countDown() }
         {e => exRef.set(e); latch.countDown() }
    latch.await()
    if(exRef.get() != null) throw exRef.get()
    ref.get
  }

  def unit[A](a: A): Par[A] = es => new Future[A] {
    def apply(cb: A => Unit)(ec: Exception => Unit): Unit = cb(a)
  }

  def fork[A](a: => Par[A]): Par[A] = es => new Future[A] {
    def apply(cb: A => Unit)(ec: Exception => Unit): Unit = eval(es)(a(es)(cb)(ec))(ec)
  }

  def eval(es: ExecutorService)(r: => Unit)(e: Exception => Unit): Unit = es.submit(new Callable[Unit] {
      def call = try {r} catch { case ex: Exception => e(ex) }
  })
}
