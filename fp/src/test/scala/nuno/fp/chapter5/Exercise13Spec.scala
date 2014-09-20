package nuno.fp.chapter5

import nuno.fp.chapter4.Exercise1.{None, Some}
import nuno.fp.chapter5.Exercise1.{Empty, Stream}
import nuno.fp.chapter5.Exercise13.{map, take, takeWhile}
import org.scalatest.{Matchers, WordSpec}

class Exercise13Spec extends WordSpec with Matchers {
  "Exercise 13" should {
    "implement map in terms of unfold" in {
      map(Stream()) {_.toString} shouldBe Empty
      map(Stream(1)) {_.toString}.toList shouldBe Stream("1").toList
      map(Stream(1, 2)) {_.toString}.toList shouldBe Stream("1", "2").toList
    }

    "implement take in terms of unfold" in {
      take(Stream(), 0) shouldBe Empty
      take(Stream(), -1) shouldBe Empty
      take(Stream(), 1) shouldBe Empty
      take(Stream(1), 1).toList shouldBe Stream(1).toList
      take(Stream(1), -1) shouldBe Empty
      take(Stream(1), 0) shouldBe Empty
      take(Stream(1, 2), 1).toList shouldBe Stream(1).toList
      take(Stream(1, 2), 2).toList shouldBe Stream(1, 2).toList
      take(Stream(1, 2, 3, 4, 5), 3).toList shouldBe Stream(1, 2, 3).toList

    }

    "implement takeWhile in terms of unfold" in {
      takeWhile(Stream()) {_ => true} shouldBe Empty
      takeWhile(Stream()) {_ => false} shouldBe Empty
      takeWhile(Stream(1)) {_ => false} shouldBe Empty
      takeWhile(Stream(1)) {_ => true}.toList shouldBe Stream(1).toList
      takeWhile(Stream(1, 2, 3)) {_ => false} shouldBe Empty
      takeWhile(Stream(1, 2, 3)) {_ => true}.toList shouldBe Stream(1, 2, 3).toList
      takeWhile(Stream(0, 2, 4, 5, 6)) {_ % 2 == 0}.toList shouldBe Stream(0, 2, 4).toList

    }

    "implement zipWith in terms of unfold" in {
      Stream().zipWith(Stream()) {(_, _) => true} shouldBe Empty
      Stream(1).zipWith(Stream()) {(_, _) => true} shouldBe Empty
      Stream().zipWith(Stream(1)) {(_, _) => true} shouldBe Empty
      Stream(0).zipWith(Stream(1)) {_ + _}.toList shouldBe Stream(1).toList
      Stream(0, 1, 2, 3).zipWith(Stream(1, 2)) {_ + _}.toList shouldBe Stream(1, 3).toList
    }

    "implement zipAll in terms of unfold" in {
      Stream().zipAll(Stream()).take(1).toList shouldBe Stream((None, None)).toList
      Stream(1).zipAll(Stream()).take(1).toList shouldBe Stream((Some(1), None)).toList
      Stream().zipAll(Stream(1)).take(1).toList shouldBe Stream((None, Some(1))).toList
      Stream(1).zipAll(Stream(1)).take(1).toList shouldBe Stream((Some(1), Some(1))).toList
      Stream(1).zipAll(Stream(1)).take(2).toList shouldBe Stream((Some(1), Some(1)), (None, None)).toList
      Stream(1, 2).zipAll(Stream(1)).take(3).toList shouldBe Stream((Some(1), Some(1)), (Some(2), None), (None, None)).toList
    }
  }
}
