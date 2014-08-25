package nuno.fp.chapter5

import nuno.fp.chapter4.Exercise1.{Some, None}
import nuno.fp.chapter5.Exercise1.{Empty, Stream}
import nuno.fp.chapter5.Exercise13.{map, take, takeWhile}
import org.scalatest.{Matchers, WordSpec}

class Exercise13Spec extends WordSpec with Matchers {
  "Exercise 13" should {
    "implement map in terms of unfold" in {
      map(Stream())(_.toString) should be(Empty)
      map(Stream(1))(_.toString).toList should be(Stream("1").toList)
      map(Stream(1, 2))(_.toString).toList should be(Stream("1", "2").toList)
    }

    "implement take in terms of unfold" in {
      take(Stream(), 0) should be(Empty)
      take(Stream(), -1) should be(Empty)
      take(Stream(), 1) should be(Empty)
      take(Stream(1), 1).toList should be(Stream(1).toList)
      take(Stream(1), -1) should be(Empty)
      take(Stream(1), 0) should be(Empty)
      take(Stream(1, 2), 1).toList should be(Stream(1).toList)
      take(Stream(1, 2), 2).toList should be(Stream(1, 2).toList)
      take(Stream(1, 2, 3, 4, 5), 3).toList should be(Stream(1, 2, 3).toList)

    }

    "implement takeWhile in terms of unfold" in {
      takeWhile(Stream())(_ => true) should be(Empty)
      takeWhile(Stream())(_ => false) should be(Empty)
      takeWhile(Stream(1))(_ => false) should be(Empty)
      takeWhile(Stream(1))(_ => true).toList should be(Stream(1).toList)
      takeWhile(Stream(1, 2, 3))(_ => false) should be(Empty)
      takeWhile(Stream(1, 2, 3))(_ => true).toList should be(Stream(1, 2, 3).toList)
      takeWhile(Stream(0, 2, 4, 5, 6))(_ % 2 == 0).toList should be(Stream(0, 2, 4).toList)

    }

    "implement zipWith in terms of unfold" in {
      Stream().zipWith(Stream())((_, _) => true) should be(Empty)
      Stream(1).zipWith(Stream())((_, _) => true) should be(Empty)
      Stream().zipWith(Stream(1))((_, _) => true) should be(Empty)
      Stream(0).zipWith(Stream(1))(_ + _).toList should be(Stream(1).toList)
      Stream(0, 1, 2, 3).zipWith(Stream(1, 2))(_ + _).toList should be(Stream(1, 3).toList)
    }

    "implement zipAll in terms of unfold" in {
      Stream().zipAll(Stream()).take(1).toList should be(Stream((None, None)).toList)
      Stream(1).zipAll(Stream()).take(1).toList should be(Stream((Some(1), None)).toList)
      Stream().zipAll(Stream(1)).take(1).toList should be(Stream((None, Some(1))).toList)
      Stream(1).zipAll(Stream(1)).take(1).toList should be(Stream((Some(1), Some(1))).toList)
      Stream(1).zipAll(Stream(1)).take(2).toList should be(Stream((Some(1), Some(1)), (None, None)).toList)
      Stream(1, 2).zipAll(Stream(1)).take(3).toList should be(Stream((Some(1), Some(1)), (Some(2), None), (None, None)).toList)
    }
  }
}
