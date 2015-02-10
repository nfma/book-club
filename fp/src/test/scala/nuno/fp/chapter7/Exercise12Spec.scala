package nuno.fp.chapter7

import java.util.concurrent.ScheduledThreadPoolExecutor

import nuno.fp.chapter7.Exercise10.{fork, unit, run => Run}
import nuno.fp.chapter7.Exercise12.choiceMap
import org.scalatest.{Matchers, WordSpec}

class Exercise12Spec extends WordSpec with Matchers {
  val es = new ScheduledThreadPoolExecutor(1)

  "Exercise 12" should {
    "implement choiceMap" in {
      Run(es)(choiceMap(fork(unit(0)))(Map(0 -> fork(unit(true)), 1 -> unit(false)))) shouldBe true
      Run(es)(choiceMap(fork(unit(1)))(Map(0 -> fork(unit(true)), 1 -> unit(false)))) shouldBe false
    }
  }
}
