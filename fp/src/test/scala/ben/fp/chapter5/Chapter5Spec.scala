package ben.fp.chapter5

import org.scalatest.{Matchers, WordSpec}


class Chapter5Spec extends WordSpec with Matchers {

  val stringStream: Stream[String] = Stream("a", "b", "c", "d", "e", "f")
  val numericStream: Stream[Int] = Stream(1, 2, 3, 4, 5, 6, 7, 8, 9)
  
  "Exercise 5.1 - Streams" should {
    "implement toList" in {

      stringStream.toList shouldBe List("a", "b", "c", "d", "e", "f")
    }
  }

  "Exercise 5.2 - Streams" should {
    "implement take" in {

      stringStream.take(3).toList shouldBe List("a", "b", "c")
    }

    "implement drop" in {

      stringStream.drop(3).toList shouldBe List("d", "e", "f")
    }
  }

  "Exercise 5.3 - Streams" should {
    "implement takeWhile" in {


      stringStream.takeWhile1(_ => true).toList shouldBe stringStream.toList
      val sublist: List[String] = List("a", "b", "c")
      stringStream.takeWhile1(s => sublist.contains(s)).toList shouldBe List("a", "b", "c")

    }
  }

  "Exercise 5.4 - Streams" should {
    "implement forAll" in {

      numericStream.forAll(_ < 10) shouldBe true
      numericStream.forAll(_ > 10) shouldBe false
      numericStream.forAll(_ < 5) shouldBe false
    }
  }

  "Exercise 5.5 - Streams" should {
    "implement takeWhile" in {

      numericStream.takeWhile(_ < 10).toList shouldBe numericStream.toList
      numericStream.takeWhile(_ < 5).toList shouldBe Stream(1, 2, 3, 4).toList
      numericStream.takeWhile(_ < 0).toList shouldBe List()
      Stream.empty[Int].takeWhile(_ < 0).toList shouldBe List()

    }
  }

  "Exercise 5.6 - Streams" should {
    "implement headOption" in {

      numericStream.headOption2 shouldBe Some(1)
      Stream().headOption2 shouldBe None
      Stream.empty.headOption2 shouldBe None
    }
  }

  "Exercise 5.7 - Streams" should {

    "implement map" in {

      Stream(1, 2, 3).map(_ + 1).toList shouldBe List(2,3,4)
      Stream.empty[Int].map(_ + 1) shouldBe Empty
    }

    "implement flatMap" in {

      Stream(1, 2).flatMap(m => stringStream.take(2).map(_ + m)).toList shouldBe List("a1","b1","a2","b2")
      Stream(1, 2).flatMap(m => Stream.empty).toList shouldBe List()
      Stream.empty[Int].flatMap(m => stringStream).toList shouldBe List()
    }

    "implement filter" in {
      
      numericStream.filter(_ % 2 == 0).toList shouldBe numericStream.toList.filter(_ % 2 == 0)
      numericStream.filter(_ > 10).toList shouldBe Nil
      Stream.empty[Int].filter(_ % 2 == 0) shouldBe Stream.empty
    }

    "implement append" in {

      numericStream.append(numericStream).toList shouldBe numericStream.toList ++ numericStream.toList
    }
  }

  "Exercise 5.8 - Streams" should {

    "provide function constant" in {

      Stream.constant(1).take(5).toList shouldBe List(1,1,1,1,1)
      Stream.constant("a").take(5).toList shouldBe List("a","a","a","a","a")
    }
  }

  "Exercise 5.9 - Streams" should {

    "provide function from" in {

      Stream.from(1).take(5).toList shouldBe List(1,2,3,4,5)
    }
  }

  "Exercise 5.10 - Streams" should {

    "provide function fibs" in {

      Stream.fibs.take(10).toList shouldBe List(0,1,1,2,3,5,8,13,21,34)
    }
  }

  "Exercise 5.11 - Streams" should {

    "provide function unfold" in {

      Stream.unfold(1)( p => Some(p, p+1)).take(5).toList shouldBe List(1,2,3,4,5)
    }
  }

  "Exercise 5.12 - Streams" should {

    "provide function fibs2 implemented using unfold" in {

      Stream.fibs2.take(10).toList shouldBe List(0,1,1,2,3,5,8,13,21,34)
    }

    "provide function from implemented using unfold" in {

      Stream.from2(0).take(10).toList shouldBe List(0,1,2,3,4,5,6,7,8,9)
    }


    "provide function constant implemented using unfold" in {

      Stream.constant2(1).take(5).toList shouldBe List(1,1,1,1,1)
      Stream.constant2("a").take(5).toList shouldBe List("a","a","a","a","a")
    }


    "provide function ones implemented using unfold" in {

      Stream.ones.take(5).toList shouldBe List(1,1,1,1,1)
    }
  }
}
