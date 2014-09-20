package nuno.fp.chapter4

import nuno.fp.chapter4.Exercise6.{Left, Right}
import org.scalatest.{Matchers, WordSpec}

class Exercise6Spec extends WordSpec with Matchers {
  "Exercise 6" should {
    "implement map" in {
      Left("exception") map identity shouldBe Left("exception")
      Left("exception") map {_ => "good"} shouldBe Left("exception")
      Right(1) map identity shouldBe Right(1)
      Right(1) map {_ => "good"} shouldBe Right("good")
    }

    "implement flatMap" in {
      Left("exception") flatMap {Right(_)} shouldBe Left("exception")
      Left("exception") flatMap {_ => Left("another exception")} shouldBe Left("exception")
      Right(1) flatMap {Right(_)} shouldBe Right(1)
      Right(1) flatMap {_ => Left("another exception")} shouldBe Left("another exception")
    }

    "implement orElse" in {
      Left("exception") orElse Right(1) shouldBe Right(1)
      Left("exception") orElse Left("another exception") shouldBe Left("another exception")
      Right(1) orElse Right(2) shouldBe Right(1)
      Right(1) orElse Left("another exception") shouldBe Right(1)
    }

    "implement map2" in {
      Left("exception").map2(Right(1)) {(_, _) => "another value"} shouldBe Left("exception")
      Left("exception").map2(Left("another exception")) {(_, _) => "another value"} shouldBe Left("exception")
      Right(1).map2(Right(2)) {(_, _) => "another value"} shouldBe Right("another value")
      Right(1).map2(Left("another exception")) {(_, _) => "another value"} shouldBe Left("another exception")
    }
  }
}
