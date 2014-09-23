package ben.fp.chapter4

import ben.fp.chapter3.{List, Nil}
import ben.fp.chapter4.Chapter4.{Either, Left, None, Right, Some}
import org.scalatest.{Matchers, WordSpec}

import scala.util.Try
import scala.util.control.NonFatal

class Chapter4Spec extends WordSpec with Matchers {

  "Exercise 4.1 - option" should {
    "implement map" in {
      None.map(identity) should be(None)
      Some(1).map(identity) should be(Some(1))
      Some(1).map(_.toString) should be(Some("1"))
    }

    "implement flatMap" in {
      None.flatMap(Some(_)) should be(None)
      Some(1).flatMap(Some(_)) should be(Some(1))
      Some(1).flatMap(v => Some(v.toString)) should be(Some("1"))
      Some(1).flatMap(v => None) should be(None)
    }

    "implement getOrElse" in {
      None.getOrElse(1) should be(1)
      Some(1).getOrElse(2) should be(1)
    }

    "implement orElse" in {
      None.orElse(Some(1)) should be(Some(1))
      Some(1).orElse(Some(2)) should be(Some(1))
    }

    "implement filter" in {
      None.filter(_ => true) should be(None)
      None.filter(_ => false) should be(None)
      Some(1).filter(_ => true) should be(Some(1))
      Some(1).filter(_ => false) should be(None)
    }
  }

  "Exercise 4.2 " should {
    "variance" in {

      None.variance(Seq(1.0, 1.0)) shouldBe Some(0.0)
      None.variance(Seq(1.0, 2.0)) shouldBe Some(0.25)
    }
  }

  "Exercise 4.3 " should {
    "map2" in {

      None.map2(Some("d"), Some("d"))(_ + _) shouldBe Some("dd")
      None.map2[String, String, String](None, Some("d"))(_ + _) shouldBe None
      None.map2(Some("d"), None)(_ + _) shouldBe None
    }
  }

  "Exercise 4.4 " should {
    "sequence" in {

      None.sequence(List(None, Some("foo"))) shouldBe None
      None.sequence(Nil) shouldBe None
      None.sequence(List(Some("d"))) shouldBe Some(List("d"))
      None.sequence(List(Some("a"), Some("b"), Some("c"))) shouldBe Some(List("a", "b", "c"))
      None.sequence(List(Some("d"), Some("d"), None)) shouldBe None
    }
  }


  "Exercise 4.5 " should {
    "traverse" in {

      def makeInt(i: String) = try {
        Some(i.toInt)
      } catch {
        case NonFatal(_) => None
      }

      None.traverse[String, String](List("1"))(i => Some(s"$i $i")) shouldBe Some(List("1 1"))
      None.traverse[String, String](List("1", "2"))(i => Some(s"$i $i")) shouldBe Some(List("1 1", "2 2"))
      None.traverse[String, Int](List("1", "2"))(i => makeInt(i)) shouldBe Some(List(1, 2))
      None.traverse[String, Int](List("1", "yo"))(i => makeInt(i)) shouldBe None

    }
  }

  "Exercise 4.6 - Either" should {
    val left: Either[String, String] = Left("boom")
    val right: Either[String, String] = Right("ok")

    "Left" in {

      left.map(_ + "foo") shouldBe left
      left.flatMap(_ => right map (_ + "this wont appear")) shouldBe left
      left.map2(right)(_ + _) shouldBe left
      left orElse right shouldBe right
    }

    "Right" in {

      right.map(_ + " dokey") shouldBe Right("ok dokey")
      right.flatMap(l => Right("dokey") map (_ + l)) shouldBe Right("dokeyok")
      right.flatMap(l => left map (_ + l)) shouldBe left
      right.flatMap(l => left map (_ + l)) shouldBe left
      right.map2(right)(_ + _) shouldBe Right("okok")
      right orElse Right("not this one") shouldBe right
    }
  }

  "Exercise 4.7 " should {
    val left: Either[String, String] = Left("boom")
    val right: Either[String, String] = Right("ok")
    val list: List[String] = List("1", "2", "3", "x")

    def toInt(s:String) = Try(Integer.parseInt(s)).toOption map ( Right(_) ) getOrElse Left("nooo")

    "traverse" in {

      right.traverse[String, String, String](list)(s => Right(s + "and")) shouldBe Right(List("1and","2and","3and", "xand"))
      right.traverse[String, String, Int](list)( toInt ) shouldBe Left("nooo")
    }

    "sequence" in {

      right.sequence[String, Int]( List( Right(1)) ) shouldBe Right(List(1))
      right.sequence[String, Int]( List( Left("oh noes")) ) shouldBe Left("oh noes")
      right.sequence[String, Int]( List( Right(1), Left("oh noes"), Right(2)) ) shouldBe Left("oh noes")
      right.sequence[String, Int]( List( Right(1), Right(2), Right(3)) ) shouldBe Right(List( 1, 2, 3))
    }
  }

}
