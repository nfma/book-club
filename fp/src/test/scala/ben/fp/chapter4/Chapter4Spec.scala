package ben.fp.chapter4

import ben.fp.chapter4.Chapter4.{None, Some}
import org.scalatest.{Matchers, WordSpec}

class Chapter4Spec extends WordSpec with Matchers {

  "Exercise 1 - option" should {
    "implement map" in {
      None.map(identity) should be(None)
      Some(1).map(identity) should be(Some(1))
      Some(1).map(_.toString) should be(Some("1"))
    }

    "implement flatMap" in {
      None.flatMap(Some(_)) should be(None)
      Some(1).flatMap(Some(_)) should be(Some(1))
      Some(1).flatMap(v => Some(v.toString)) should be(Some("1"))
      Some(1).flatMap(v => None) should be(None)
    }

    "implement getOrElse" in {
      None.getOrElse(1) should be(1)
      Some(1).getOrElse(2) should be(1)
    }

    "implement orElse" in {
      None.orElse(Some(1)) should be(Some(1))
      Some(1).orElse(Some(2)) should be(Some(1))
    }

    "implement filter" in {
      None.filter(_ => true) should be(None)
      None.filter(_ => false) should be(None)
      Some(1).filter(_ => true) should be(Some(1))
      Some(1).filter(_ => false) should be(None)
    }
  }
}
