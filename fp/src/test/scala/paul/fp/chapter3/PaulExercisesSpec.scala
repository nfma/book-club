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
        List.tail(Nil)
      }
    }
  }

  describe("Exercise 3") {
    describe("setHead") {
      it("replace the first element of a List with a different value") {
        List.setHead(List(1,2,3), 5) should equal(List(5,2,3))
        List.setHead(List(), 5) should equal(List(5))
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
      }
    }
  }

  describe("Exercise 8") {
    describe("see what happens when you pass Nil and Cons to foldRight ") {
      it("should answer the question in the test") {
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

  describe("Exercise 10") {
    describe("foldLeft") {
      it("should go through list from head to tail and pass each element to function f") {
        List.foldLeft(List(1,2,3,4), 0)((b,a) => b+a) should equal(10)
        List.foldLeft(List(1,2,3,4), "A")((b,a) => b+a) should equal("A1234")
      }
    }
  }

  describe("Exercise 11") {
    describe("sumUsingFoldLeft") {
      it("should calculate sum using foldLeft") {
        List.sumUsingFoldLeft(List(4,2,3,4)) should equal(13)
      }
    }
    describe("productUsingFoldLeft") {
      it("should calculate sum using foldLeft") {
        List.productUsingFoldLeft(List(1,2,3,4)) should equal(24)
      }
    }
  }

  describe("Exercise 12") {
    describe("reverseUsingFold") {
      it("should reverse a list using a fold") {
        List.reverseUsingFoldLeft(List(1,2,3)) should equal(List(3,2,1))
        List.reverseUsingFoldLeft(List(1,2,3,4)) should equal(List(4,3,2,1))
      }
    }
  }

  describe("Exercise 13") {
    describe("todo!") {
    }
  }

  describe("Exercise 14") {
    describe("appendUsingFold") {
      it("should append one list to the other using fold") {
        List.appendUsingFoldLeft(List(1,2,3), List(4,5,6)) should equal(List(1,2,3,4,5,6))
      }
    }
  }

  describe("Exercise 16") {
    describe("transformUsingFoldRight") {
      it("should transform a list of elements by adding 1 to each one.") {
        List.transformUsingFoldRight(List(1,2,3), (a:Int) => a + 1) should equal(List(2,3,4))
      }
    }
    describe("transformUsingFoldLeft") {
      it("should transform a list of elements by adding 1 to each one.") {
        List.transformUsingFoldLeft(List(1,2,3), (a:Int) => a + 1) should equal(List(2,3,4))
      }
    }
  }

  describe("Exercise 17") {
    describe("convertDoubleToString in list") {
      it("should convert each element in a list from double to String.") {
        List.transformUsingFoldRight(List(1,2,3), (a:Int) => a.toString) should equal(List("1","2","3"))
      }
    }
  }

  describe("map") {
    it("should transform a list of elements by multiplying each element by 3.") {
      List.map(List(1,2,3))((a:Int) => a * 3) should equal(List(3,6,9))
    }
  }

  describe("filter") {
    it("should remove odd numbers from a list.") {
      List.filter(List(1,2,3,4))((a:Int) => a % 2 == 0) should equal(List(2,4))
    }
    it("should remove even numbers from a list.") {
      List.filter(List(1,2,3,4))((a:Int) => a % 2 != 0) should equal(List(1,3))
    }
  }

}
