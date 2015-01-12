package nuno.fp.chapter7

import java.util.concurrent.TimeUnit.MILLISECONDS
import java.util.concurrent.{ScheduledThreadPoolExecutor, TimeoutException}

import nuno.fp.chapter7.Exercise3.Par.{foldPar, parFilter, map3, unit}
import org.scalatest.{Matchers, WordSpec}

class Exercise6Spec extends WordSpec with Matchers {
  val es = new ScheduledThreadPoolExecutor(2)

   "parFilter" should {
     "filter a list in parallel" in {
       parFilter(Nil: List[Int])(_ => false)(es).get shouldBe Nil
       parFilter(List(1))(_ => false)(es).get shouldBe Nil
       parFilter(List(1))(_ => true)(es).get shouldBe List(1)
       parFilter(List(1, 2, 3))(_ => true)(es).get shouldBe List(1, 2, 3)
       parFilter(List(1, 2, 3))(_ % 2 == 0)(es).get shouldBe List(2)
       parFilter(List(1, 2, 3))(i => {Thread.sleep(20); i % 2 == 0})(es).get(100, MILLISECONDS) shouldBe List(2)
       intercept[TimeoutException] {
         parFilter(List(1, 2, 3))(i => {Thread.sleep(34); i % 2 == 0})(es).get(100, MILLISECONDS)
       }
     }
   }

    "foldPar" should {
      def sum(s: IndexedSeq[Int]) = foldPar(s)(0)(_+_)
      def max(s: IndexedSeq[Int]) = foldPar(s)(0)((a, b) => if (a > b) a else b)
      def numWords(s: List[String]) = foldPar(s)(0)(_.split(" ").size + _)

      "sum in parallel" in {
        sum(IndexedSeq())(es).get shouldBe 0
        sum(IndexedSeq(1))(es).get shouldBe 1
        sum(IndexedSeq(1, 2))(es).get shouldBe 3
        sum(IndexedSeq(1, 2, 3))(es).get shouldBe 6
      }

      "take max in parallel" in {
        max(IndexedSeq())(es).get shouldBe 0
        max(IndexedSeq(1))(es).get shouldBe 1
        max(IndexedSeq(1, 2))(es).get shouldBe 2
        max(IndexedSeq(1, 3, 2))(es).get shouldBe 3
        max(IndexedSeq(1, 3, 4, 2))(es).get shouldBe 4
      }

      "count words in parallel" in {
        numWords(Nil)(es).get shouldBe 0
        numWords(List("one"))(es).get shouldBe 1
        numWords(List("one two"))(es).get shouldBe 2
        numWords(List("one", "two"))(es).get shouldBe 2
        numWords(List("one", "two three"))(es).get shouldBe 3
        numWords(List("one", "two three", "four"))(es).get shouldBe 4
        numWords(List("one", "two three", "four five six seven"))(es).get shouldBe 7
      }

      "map3" in {
        map3(unit(1), unit(2), unit(3))((a, b, c) => a + b + c)(es).get shouldBe 6
      }
    }
 }
