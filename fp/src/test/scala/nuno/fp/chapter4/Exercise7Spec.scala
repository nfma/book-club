package nuno.fp.chapter4

import nuno.fp.chapter3.{Nil, List}
import nuno.fp.chapter4.Exercise6.{Left, Right}
import nuno.fp.chapter4.Exercise7.{traverse, sequence}
import org.scalatest.{Matchers, WordSpec}

class Exercise7Spec extends WordSpec with Matchers {
  "Exercise 7" should {
    "implement sequence" in {
      sequence(Nil) should be(Right(Nil))
      sequence(List(Right(1))) should be(Right(List(1)))
      sequence(List(Left("exception"))) should be(Left("exception"))
      sequence(List(Right(1), Left("exception"))) should be(Left("exception"))
      sequence(List(Left("exception"), Left("exception"))) should be(Left("exception"))
      sequence(List(Right(1), Right(2))) should be(Right(List(1, 2)))
      sequence(List(Right(1), Left("exception"), Right(2))) should be(Left("exception"))
    }
    
    "implement traverse" in {
      traverse(Nil)(_ => Right(1)) should be(Right(Nil))
      traverse(Nil)(v => Left("exception")) should be(Right(Nil))
      traverse(List(1))(_ => Left("exception")) should be(Left("exception"))
      traverse(List(1))(v => Right(v)) should be(Right(List(1)))
      traverse(List(1, 2, 3))(v => Right(v)) should be(Right(List(1, 2, 3)))
      traverse(List(1, 2, 3))(v => if (v % 2 == 0) Right(v) else Left("exception")) should be(Left("exception"))
    }
  }
}
