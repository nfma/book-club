package nuno.fp.chapter2

import nuno.fp.chapter2.Exercise2.isSorted
import org.scalatest.{Matchers, WordSpec}

class Exercise2Spec extends WordSpec with Matchers {
  "Exercise 2" should {
    "return true when asked if an empty array is sorted" in {
      isSorted[Int](Array(), _ < _) should be(true)
    }

    "return true when asked if a single element array is sorted" in {
      isSorted[Int](Array(1), _ < _) should be(true)
    }

    "return true when asked if a double element array is sorted when sorted" in {
      isSorted[Int](Array(1, 2), _ < _) should be(true)
    }

    "return false when asked if a double element array is sorted when sorted in the reverse order" in {
      isSorted[Int](Array(1, 2), _ > _) should be(false)
    }

    "return true when asked if a double element array is sorted with the equal sort function when sorted all elements are equal" in {
      isSorted[Int](Array(1, 1), _ == _) should be(true)
    }

    "return false when asked if a double element array is sorted with the equal sort function when sorted all elements are different" in {
      isSorted[Int](Array(1, 2), _ == _) should be(false)
    }

    "return true when asked if a triple element array is sorted when sorted" in {
      isSorted[Int](Array(1, 2, 3), _ < _) should be(true)
    }

    "return false when asked if a triple element array is sorted when not sorted" in {
      isSorted[Int](Array(1, 3, 2), _ < _) should be(false)
    }
  }

}
