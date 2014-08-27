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
    setHead(3, List(4)) shouldBe List(3)
    setHead(3, List(1, 4)) shouldBe List(3, 4)
  }

  "Exercise 3 - drop" in {

    intercept[IllegalArgumentException](drop(Nil, 1))
    drop(Nil, 0) shouldBe Nil
    drop(List(1), 0) shouldBe List(1)
    drop(List(1), 1) shouldBe Nil
    drop(List(1, 2), 1) shouldBe List(2)
    drop(List(1, 2, 3), 1) shouldBe List(2, 3)
    drop(List(1, 2, 3, 4), 2) shouldBe List(3, 4)
  }

  "Exercise 4 - dropWhile" in {

    val greaterThanTwo = (n:Int) => n > 2

    dropWhile(Nil, greaterThanTwo) shouldBe Nil
    dropWhile(List(3, 4, 5), greaterThanTwo) shouldBe Nil
    dropWhile(List(1), greaterThanTwo) shouldBe List(1)
    dropWhile(List(2, 1), greaterThanTwo) shouldBe List(2, 1)
    dropWhile(List(3, 2, 1), greaterThanTwo) shouldBe List(2, 1)
  }


}
