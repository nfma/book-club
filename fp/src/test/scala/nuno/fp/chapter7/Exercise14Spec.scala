package nuno.fp.chapter7

import java.util.concurrent.ScheduledThreadPoolExecutor

import nuno.fp.chapter7.Exercise10.{unit, run => Run}
import nuno.fp.chapter7.Exercise14.{join, joinFM, flatMap}
import org.scalatest.{Matchers, WordSpec}

class Exercise14Spec extends WordSpec with Matchers {
  val es = new ScheduledThreadPoolExecutor(1)

  "Exercise 14" should {
    "implement join" in {
      Run(es)(join(unit(unit(5)))) shouldBe 5
    }

    "implement joinFM (join in terms of flatMap)" in {
      Run(es)(joinFM(unit(unit(5)))) shouldBe 5
    }

    "implement flatMap (in terms of join)" in {
      Run(es)(flatMap(unit(5))(i => unit(false))) shouldBe false
    }
  }
}
