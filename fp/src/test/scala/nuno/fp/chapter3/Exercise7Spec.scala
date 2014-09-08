package nuno.fp.chapter3

import org.scalatest.WordSpec

class Exercise7Spec extends WordSpec {
  "Exercise 7" should {
    "answer questions" in {
      println("No, it can't; Unless we throw an exception on the reduce function, there's no way to stop the foldRight from going through all the elements")
      println( """I can't see how this could be done without changing fold. We could provide an additional function or element to fold that would be able to
                 |short circuit if the result arrived at the same value as this value or function, for example 0.0 in a product and not bother to carry on recursing""".stripMargin)
    }
  }
}
