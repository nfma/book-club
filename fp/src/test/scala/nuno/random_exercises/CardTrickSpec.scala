package nuno.random_exercises

import nuno.random_exercises.CardTrick.mkWinner
import org.scalacheck.Gen
import org.scalatest.prop.PropertyChecks
import org.scalatest.{Matchers, PropSpec}

class CardTrickSpec extends PropSpec with PropertyChecks with Matchers {
  val cards = Gen.oneOf(AceSpades, TwoSpades, ThreeSpades, FourSpades, FiveSpades, SixSpades, SevenSpades, EightSpades, NineSpades, TenSpades, JackSpades, QueenSpades, KingSpades,
    AceHearts, TwoHearts, ThreeHearts, FourHearts, FiveHearts, SixHearts, SevenHearts, EightHearts, NineHearts, TenHearts, JackHearts, QueenHearts, KingHearts,
    AceClubs, TwoClubs, ThreeClubs, FourClubs, FiveClubs, SixClubs, SevenClubs, EightClubs, NineClubs, TenClubs, JackClubs, QueenClubs, KingClubs,
    AceDiamonds, TwoDiamonds, ThreeDiamonds, FourDiamonds, FiveDiamonds, SixDiamonds, SevenDiamonds, EightDiamonds, NineDiamonds, TenDiamonds, JackDiamonds, QueenDiamonds, KingDiamonds)
  val atLeastOneCard = Gen.nonEmptyContainerOf[Seq, Card](cards)
  val trump = Gen.option(Gen.oneOf(Spades, Clubs, Hearts, Diamonds))
  val trumpAndCards = Gen.zip(trump, atLeastOneCard)

  implicit override val generatorDrivenConfig = PropertyCheckConfig(minSize = 4, maxSize = 20)
  implicit val ordering = new Ordering[Card] {
    override def compare(x: Card, y: Card): Int = x.order.compareTo(y.order)
  }

  property("the winner's card is the biggest card of the trump suit or lead suit") {
    forAll(trumpAndCards) {
      case (suit@Some(s), cs) if cs.exists(_.suit == s) => cs.filter(_.suit == s).max shouldBe mkWinner(suit)(cs)
      case (_, cs) => cs.filter(_.suit == cs.head.suit).max shouldBe mkWinner(None)(cs)
    }
  }
}
