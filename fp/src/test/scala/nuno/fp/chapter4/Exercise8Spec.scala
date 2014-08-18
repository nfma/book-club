package nuno.fp.chapter4

import org.scalatest.WordSpec

class Exercise8Spec extends WordSpec {
  "Exercise 8" should {
    "answer the questions" in {
      println("Either be able to aggregate the errors into the same type ie (E, E) => E or instead of returning an E return a List[E] on the Left of the Either")
      println("Ideally the signature of mkPerson to return Either[List[String], Person] instead of Either[String, Person]")
      println("Yes, this new data type would have to store a list of errors as specified above")
      println("The functions from Either would have to accommodate the fact that now they would be returning a List instead of a String, so they would need to append the errors as they were found, instead of ignoring them after one was found")
    }
  }

}
