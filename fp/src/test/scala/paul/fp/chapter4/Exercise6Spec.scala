package paul.fp.chapter4

import org.scalatest.{Matchers, WordSpec}
import paul.fp.chapter4.Exercise6.{Left, Right}

class Exercise6Spec extends WordSpec with Matchers {
  "Exercise 6" should {
    "implement map" in {
      Left("exception").map(_ => "good") should be(Left("exception"))
      Left("another exception").map(_ => "should not map this") should be(Left("another exception"))
      Right(1).map(_ => "good") should be(Right("good"))
    }
    
    "implement flatMap" in {
      Left("exception").flatMap(_ => Right(1)) should be(Left("exception"))
      Left("exception").flatMap(_ => Left("another exception")) should be(Left("exception"))
      Right(1).flatMap(_ => Right(2)) should be(Right(2))
      Right(1).flatMap(_ => Left("another exception")) should be(Left("another exception"))
    }
    
    "implement orElse" in {
      Left("exception").orElse(Right(1)) should be(Right(1))
      Left("exception").orElse(Left("another exception")) should be(Left("another exception"))
      Right(1).orElse(Right(2)) should be(Right(1))
      Right(1).orElse(Left("another exception")) should be(Right(1))
    }
    
    "implement map2" in {
      Left("exception").map2(Right(1))((_, _) => "another value") should be(Left("exception"))
      Left("exception").map2(Left("another exception"))((_, _) => "another value") should be(Left("exception"))
      Right(1).map2(Right(2))(f = (_, _) => "another value") should be(Right("another value"))
//      Right(1).map2(Left("another exception"))((_, _) => "another value") should be(Left("another exception"))
    }
  }
}
