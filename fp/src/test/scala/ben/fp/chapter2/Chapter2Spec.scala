package ben.fp.chapter2

import org.scalatest.{Matchers, WordSpec}

class Chapter2Spec extends WordSpec with Matchers {

  import ben.fp.chapter2.Chapter2._

  "Exercise 1 - fibs" in {

    val fibs = List(0,1,1,2,3,5,8,13,21)

    (0 to fibs.size -1) map ( nth => fib(nth) shouldBe fibs(nth) )
  }

  "Exercise 2 - isSorted" in {

     val sortedInts= List(0,1,3)
     val unsortedInts= List(1,0,3)
     val sortedStrings= List("0","1","3")
     val unsortedStrings= List("1","0","3")

    isSorted(sortedInts toArray, (a:Int,b:Int) => {a < b}) shouldBe true
    isSorted(unsortedInts toArray,(a:Int,b:Int) => {a < b}) shouldBe false

    isSorted(sortedStrings toArray, (a:String,b:String) => {a < b}) shouldBe true
    isSorted(unsortedStrings toArray, (a:String,b:String) => {a < b}) shouldBe false
  }

  "Exercise 3 - curry" in {

    val twoargFunction = (a:Int, b:Int) => a + b
    val oneArgFunc = curry(twoargFunction)
    val addsFive = oneArgFunc(5)

    addsFive(5) shouldBe 10
  }

  "Exercise 4 - uncurry" in {

    val oneArgFunc = curry((a:Int, b:Int) => a + b)
    val twoArgFunc = uncurry(oneArgFunc)

    twoArgFunc(10, 10) shouldBe 20
  }

  "Exercise 5 - conpose" in {

    val s1 = (name:String) => s"string1[$name]"
    val s2  = (name:String) => s"string2[$name]"

    val composed = compose(s1, s2)

    composed("yikes") shouldBe "string1[string2[yikes]]"
  }
}
