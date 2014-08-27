package ben.fp.chapter3

import org.scalatest.{Matchers, WordSpec}

class Chapter3Spec extends WordSpec with Matchers {

  import ben.fp.chapter3.List._

  "Exercise 2 - tail" in {

    tail(List(3)) shouldBe Nil
    tail(List(2, 3)) shouldBe List(3)
    tail(List(1, 2, 3)) shouldBe List(2, 3)
  }

  "Exercise 3 - setHead" in {

    setHead(5, Nil) shouldBe List(5)
    setHead(3, List(4)) shouldBe List(3,4)
  }
}
