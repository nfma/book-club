package nuno.fp.chapter7

import java.util.concurrent.ScheduledThreadPoolExecutor

import org.scalatest.{Matchers, WordSpec}

class Exercise4Spec extends WordSpec with Matchers {
  "asyncF" should {
    "return a lazy unit" in {
      val es = new ScheduledThreadPoolExecutor(1)
      Exercise4.asyncF((i: Int) => "cool")(1)(es).get shouldBe "cool"
    }
  }
}
