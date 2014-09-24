package ben.fp.chapter5

import org.scalatest.{Matchers, WordSpec}


class Chapter5Spec extends WordSpec with Matchers {

  val stream: Stream[String] = Stream("a", "b", "c", "d", "e", "f")

  "Exercise 5.1 - Streams" should {
    "implement toList" in {

      stream.toList shouldBe List("a", "b", "c", "d", "e", "f")
    }
  }

  "Exercise 5.2 - Streams" should {
    "implement take" ignore {

      stream.take(3).toList shouldBe List("a","b","c")
    }
  }


  "Exercise 5.2 - Streams" should {
    "implement drop" ignore {

      stream.drop(3).toList shouldBe List("d", "e", "f")
    }
  }

}
