package nuno.fp.chapter7

import java.util.concurrent.{TimeoutException, ScheduledThreadPoolExecutor, TimeUnit}

import nuno.fp.chapter7.Exercise3.Par.lazyUnit
import nuno.fp.chapter7.Exercise3.{Map2Maker, Par}
import org.scalatest.{Matchers, WordSpec}

class Exercise3Spec extends WordSpec with Matchers {
  val es = new ScheduledThreadPoolExecutor(1)

  "map2" should {
    "respect timeouts when values are already computed" in {
      Par.unit(1).map2(Par.unit(2)) {_ + _}(es).get() shouldBe 3
    }

    "respect timeouts when values take time to be computed" in {
      val f = lazyUnit({Thread.sleep(100); 1})
      val g = lazyUnit({Thread.sleep(100); 2})
      f.map2(g) {_ + _}(es).get(300, TimeUnit.MILLISECONDS) shouldBe 3
    }

    "respect timeouts when values take too much time to be computed" in {
      val f = lazyUnit({Thread.sleep(500); 1})
      val g = lazyUnit({Thread.sleep(501); 2})
      intercept[TimeoutException] {
        f.map2(g) {_ + _}(es).get(1, TimeUnit.SECONDS)
      }
    }

    "respect timeouts when even the first value take too much time to be computed" in {
      val f = lazyUnit({Thread.sleep(1500); 1})
      val g = lazyUnit(2)
      intercept[TimeoutException] {
        f.map2(g) {_ + _}(es).get(1, TimeUnit.SECONDS)
      }
    }
  }
}
