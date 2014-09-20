package nuno.fp.chapter4

import nuno.fp.chapter4.Exercise1.{None, Some}
import org.scalatest.{Matchers, WordSpec}

class Exercise1Spec extends WordSpec with Matchers {
  "Exercise 1" should {
    "implement map" in {
      None map identity shouldBe None
      Some(1) map identity shouldBe Some(1)
      Some(1) map {_.toString} shouldBe Some("1")
    }

    "implement flatMap" in {
      None flatMap {Some(_)} shouldBe None
      Some(1) flatMap {Some(_)} shouldBe Some(1)
      Some(1) flatMap {v => Some(v.toString)} shouldBe Some("1")
      Some(1) flatMap {_ => None} shouldBe None
    }

    "implement getOrElse" in {
      None getOrElse 1 shouldBe 1
      Some(1) getOrElse 2 shouldBe 1
    }

    "implement orElse" in {
      None orElse Some(1) shouldBe Some(1)
      Some(1) orElse Some(2) shouldBe Some(1)
    }

    "implement filter" in {
      None filter {_ => true} shouldBe None
      None filter {_ => false} shouldBe None
      Some(1) filter {_ => true} shouldBe Some(1)
      Some(1) filter {_ => false} shouldBe None
    }
  }
}
