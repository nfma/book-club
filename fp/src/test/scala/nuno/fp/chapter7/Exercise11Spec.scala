package nuno.fp.chapter7

import java.util.concurrent.ScheduledThreadPoolExecutor

import nuno.fp.chapter7.Exercise10.{fork, unit, run => Run}
import nuno.fp.chapter7.Exercise11.{choice, choiceN}
import org.scalatest.{Matchers, WordSpec}

class Exercise11Spec extends WordSpec with Matchers {
  val es = new ScheduledThreadPoolExecutor(1)

  "Exercise 11" should {
    "implement choiceN" in {
      Run(es)(choiceN(fork(unit(0)))(List(unit(true), unit(false)))) shouldBe true
      Run(es)(choiceN(fork(unit(1)))(List(unit(true), unit(false)))) shouldBe false
    }

    "implement choice" in {
      Run(es)(choice(fork(unit(true)))(unit(0), unit(1))) shouldBe 0
      Run(es)(choice(fork(unit(false)))(unit(0), unit(1))) shouldBe 1
    }
  }
}
