package nuno.fp.chapter7

import java.util.concurrent.ScheduledThreadPoolExecutor

import nuno.fp.chapter7.Exercise10.{fork, unit, run => Run}
import org.scalatest.{Matchers, WordSpec}

class Exercise10Spec extends WordSpec with Matchers {
  val es = new ScheduledThreadPoolExecutor(1)

  "Exercise 10" should {
    "have deadlock free futures" in {
      Run(es)(unit(5)) shouldBe 5
      Run(es)(fork(unit(5))) shouldBe 5
      intercept[IllegalArgumentException] {
        Run(es)(unit(throw new IllegalArgumentException()))
      }
      intercept[IllegalArgumentException] {
        Run(es)(fork(unit(throw new IllegalArgumentException())))
      }
    }
  }
}
