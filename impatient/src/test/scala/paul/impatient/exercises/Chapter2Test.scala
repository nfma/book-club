package paul.impatient.exercises

import org.scalatest.{FunSpec, Matchers}
import paul.impatient.exercises.Chapter2._

class Chapter2Test extends FunSpec with Matchers {

  describe("exercise 1") {
    it("should produce 1 when input is 2") {
      exercise1 should be(true)
    }
  }
}
