package nuno.fp.chapter5

import nuno.fp.chapter5.Exercise1.{Empty, Stream}
import nuno.fp.chapter5.Exercise12.{ones, constant, from, fibs}
import org.scalatest.{Matchers, WordSpec}

class Exercise12Spec extends WordSpec with Matchers {
  "Exercise 12" should {
    "implement ones in terms of unfold" in {
      ones.take(0) should be(Empty)
      ones.take(1).toList should be(Stream(1).toList)
      ones.take(2).toList should be(Stream(1, 1).toList)
      ones.take(5).toList should be(Stream(1, 1, 1, 1, 1).toList)
    }

    "implement constant in terms of unfold" in {
      constant(1).take(0) should be(Empty)
      constant(1).take(1).toList should be(Stream(1).toList)
      constant(1).take(5).toList should be(Stream(1, 1, 1, 1, 1).toList)
    }

    "implement from in terms of unfold" in {
      from(0).take(0) should be(Empty)
      from(0).take(1).toList should be(Stream(0).toList)
      from(0).take(2).toList should be(Stream(0, 1).toList)
      from(0).take(5).toList should be(Stream(0, 1, 2, 3, 4).toList)
    }
    
    "implement fibs in terms of unfold" in {
      fibs.take(0) should be(Empty)
      fibs.take(1).toList should be(Stream(0).toList)
      fibs.take(2).toList should be(Stream(0, 1).toList)
      fibs.take(3).toList should be(Stream(0, 1, 1).toList)
      fibs.take(4).toList should be(Stream(0, 1, 1, 2).toList)
      fibs.take(5).toList should be(Stream(0, 1, 1, 2, 3).toList)
      fibs.take(6).toList should be(Stream(0, 1, 1, 2, 3, 5).toList)
      fibs.take(7).toList should be(Stream(0, 1, 1, 2, 3, 5, 8).toList)      
    }
  }
}
