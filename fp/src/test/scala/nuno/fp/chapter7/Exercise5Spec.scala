package nuno.fp.chapter7

import java.util.concurrent.{TimeoutException, ScheduledThreadPoolExecutor}
import scala.concurrent.duration._

import nuno.fp.chapter7.Exercise3.Par
import nuno.fp.chapter7.Exercise3.Par.sequence
import org.scalatest.{Matchers, WordSpec}

class Exercise5Spec extends WordSpec with Matchers {
  "sequence" should {
    "take a list of par and return a par of a list" in {
      val es = new ScheduledThreadPoolExecutor(1)
      sequence(Nil)(es).get shouldBe Nil
      sequence(List(Par.unit(0), Par.unit(1)))(es).get shouldBe List(0, 1)
      sequence(List[Par[Int]](Par.lazyUnit({Thread.sleep(50); 1})))(es).get(100, MILLISECONDS) shouldBe List(1)
      intercept[TimeoutException] {
        sequence(List[Par[Int]](Par.lazyUnit({Thread.sleep(200); 1})))(es).get(100, MILLISECONDS)
      }
    }
  }
}
