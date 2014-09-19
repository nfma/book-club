package ben.fp.chapter4

import ben.fp.chapter4.Chapter4.{None, Some}
import org.scalatest.{Matchers, WordSpec}

import scala.util.Try

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

      None.traverse[String, String](List("1"))( i => Some(s"$i $i") ) shouldBe Some(List("1 1"))
    }
  }
}
