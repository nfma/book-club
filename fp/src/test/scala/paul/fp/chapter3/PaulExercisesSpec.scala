package paul.fp.chapter3

import org.scalatest.{FunSpec, Matchers}

class PaulExercisesSpec extends FunSpec with Matchers {

  describe("Exercise 1") {
    describe("what will be the result of the following match expression") {
    }
  }

  describe("Exercise 2") {
    describe("tail") {
      it("should remove the first element of a list") {
        List.tail(List(1,2,3)) should equal(List(2,3))
      }
    }
  }

  describe("Exercise 3") {
    describe("setHead") {
      it("replace the first element of a List with a different value") {
        List.setHead(List(1,2,3), 5) should equal(List(5,2,3))
      }
    }
  }

  describe("Exercise 4") {
    describe("drop") {
      it("generalize tail to the function drop which removes the first n elements from a list") {
        List.drop(List(1,2,3,4), 2) should equal(List(3,4))
      }
    }
  }

  describe("Exercise 5") {
    describe("dropWhile") {
      it("should remove elements of a list that match a predicate") {
        List.dropWhile(List(1,2,3,4), (a:Int) => a % 2 == 0) should equal(List(3,1))
      }
    }
  }

  describe("Exercise 6") {
    describe("init") {
      it("should return all but the last element of a list, why not constant time") {
      }
    }
  }

  describe("Exercise 7") {
    describe("init") {
      it("should answer the question in the test") {
//        answer should not equal("")
      }
    }
  }

  describe("Exercise 8") {
    describe("see what happens when you pass Nil and Cons to foldRight ") {
      it("should answer the question in the test") {
//        foldRight(List(1,2,3), Nil:List[Int])(Cons(_,_))
      }
    }
  }

  describe("Exercise 9") {
    describe("length") {
      it("should compute the length of a list using foldRight") {
        List.length(List(1,2,3,4)) should equal(4)
      }
    }
  }

  describe("Exercise 10.1") {
    describe("foldRight should explode with a stack overflow error") {
      it("existing foldRight is not tail recursive so will result in a stack overflow for long lists") {
        var longList:List[Int] = List()
        for(i <- 1 to 325000)
          longList = List.append(longList, List(i))

        intercept[java.lang.StackOverflowError] {
          List.foldRight(longList,0)((x,y) => x+y)
        }
      }
    }
  }
}
