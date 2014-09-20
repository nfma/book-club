package nuno.fp.chapter4

import nuno.fp.chapter3.{List, Nil}
import nuno.fp.chapter4.Exercise6.{Left, Right}
import nuno.fp.chapter4.Exercise7.{sequence, traverse}
import org.scalatest.{Matchers, WordSpec}

class Exercise7Spec extends WordSpec with Matchers {
  "Exercise 7" should {
    "implement sequence" in {
      sequence(Nil) shouldBe Right(Nil)
      sequence(List(Right(1))) shouldBe Right(List(1))
      sequence(List(Left("exception"))) shouldBe Left("exception")
      sequence(List(Right(1), Left("exception"))) shouldBe Left("exception")
      sequence(List(Left("exception"), Left("exception"))) shouldBe Left("exception")
      sequence(List(Right(1), Right(2))) shouldBe Right(List(1, 2))
      sequence(List(Right(1), Left("exception"), Right(2))) shouldBe Left("exception")
    }

    "implement traverse" in {
      traverse(Nil) {Right(_)} shouldBe Right(Nil)
      traverse(Nil) {_ => Left("exception")} shouldBe Right(Nil)
      traverse(List(1)) {_ => Left("exception")} shouldBe Left("exception")
      traverse(List(1)) {Right(_)} shouldBe Right(List(1))
      traverse(List(1, 2, 3)) {Right(_)} shouldBe Right(List(1, 2, 3))
      traverse(List(1, 2, 3)) {
        case v if v % 2 == 0 => Right(v)
        case _ => Left("exception")
      } shouldBe Left("exception")
    }
  }
}
