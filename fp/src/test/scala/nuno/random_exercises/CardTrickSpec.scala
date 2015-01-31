package nuno.random_exercises

import nuno.random_exercises.CardTrick.mkWinner
import org.scalacheck.Gen
import org.scalatest.prop.PropertyChecks
import org.scalatest.{Matchers, PropSpec}

class CardTrickSpec extends PropSpec with PropertyChecks with Matchers {
  val suits = Gen.oneOf(Spades, Clubs, Hearts, Diamonds)
  val ranks = Gen.oneOf(Ace, Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen, King)
  val cards = for {
    rank <- ranks
    suit <- suits
  } yield Card(rank, suit)
  val atLeastOneCard = Gen.nonEmptyContainerOf[Seq, Card](cards)

  val trumpAndCardsWithTrumpCards = for {
    s <- suits
    cs <- atLeastOneCard if cs.exists(_.suit == s)
  } yield (s, cs)

  val trumpAndCardsWithoutTrumpCards = for {
    s <- suits
    cs <- atLeastOneCard if cs.forall(_.suit != s)
  } yield (s, cs)

  val NoTrumpAndCards = for {
    cs <- atLeastOneCard
    s <- Gen.const(None)
  } yield (s, cs)

  implicit override val generatorDrivenConfig = PropertyCheckConfig(minSize = 2, maxSize = 10)
  implicit val ordering = new Ordering[Card] {
    override def compare(x: Card, y: Card): Int = x.rank.order.compareTo(y.rank.order)
  }

  property("the winner's card is the biggest card of the trump suit") {
    forAll(trumpAndCardsWithTrumpCards) {
      case (suit, cs) => cs.filter(_.suit == suit).max shouldBe mkWinner(Some(suit))(cs)
    }
  }

  property("the winner's card is the biggest card of the lead suit when no cards in the trump suit") {
    forAll(trumpAndCardsWithoutTrumpCards) {
      case (suit, cs) => cs.filter(_.suit == cs.head.suit).max shouldBe mkWinner(Some(suit))(cs)
    }
  }

  property("the winner's card is the biggest card of the lead suit when no trump") {
    forAll(NoTrumpAndCards) {
      case (None, cs) => cs.filter(_.suit == cs.head.suit).max shouldBe mkWinner(None)(cs)
    }
  }
}
