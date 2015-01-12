package nuno.random_exercises

import scala.language.postfixOps

sealed trait Suit {
  val suit: Suit
}

trait Spades extends Suit {
  val suit = Spades
}
trait Hearts extends Suit {
  val suit = Hearts
}
trait Clubs extends Suit {
  val suit = Clubs
}
trait Diamonds extends Suit {
  val suit = Diamonds
}

case object Spades extends Spades
case object Hearts extends Hearts
case object Clubs extends Clubs
case object Diamonds extends Diamonds

sealed trait Card extends Suit {
  def order: Int
}

trait Ace extends Card {
  val order = 13
}
trait Two extends Card {
  val order = 1
}
trait Three extends Card {
  val order = 2
}
trait Four extends Card {
  val order = 3
}
trait Five extends Card {
  val order = 4
}
trait Six extends Card {
  val order = 5
}
trait Seven extends Card {
  val order = 6
}
trait Eight extends Card {
  val order = 7
}
trait Nine extends Card {
  val order = 8
}
trait Ten extends Card {
  val order = 9
}
trait Jack extends Card {
  val order = 10
}
trait Queen extends Card {
  val order = 11
}
trait King extends Card {
  val order = 12
}

case object AceSpades extends Ace with Spades
case object TwoSpades extends Two with Spades
case object ThreeSpades extends Three with Spades
case object FourSpades extends Four with Spades
case object FiveSpades extends Five with Spades
case object SixSpades extends Six with Spades
case object SevenSpades extends Seven with Spades
case object EightSpades extends Eight with Spades
case object NineSpades extends Nine with Spades
case object TenSpades extends Ten with Spades
case object JackSpades extends Jack with Spades
case object QueenSpades extends Queen with Spades
case object KingSpades extends King with Spades

case object AceDiamonds extends Ace with Diamonds
case object TwoDiamonds extends Two with Diamonds
case object ThreeDiamonds extends Three with Diamonds
case object FourDiamonds extends Four with Diamonds
case object FiveDiamonds extends Five with Diamonds
case object SixDiamonds extends Six with Diamonds
case object SevenDiamonds extends Seven with Diamonds
case object EightDiamonds extends Eight with Diamonds
case object NineDiamonds extends Nine with Diamonds
case object TenDiamonds extends Ten with Diamonds
case object JackDiamonds extends Jack with Diamonds
case object QueenDiamonds extends Queen with Diamonds
case object KingDiamonds extends King with Diamonds

case object AceClubs extends Ace with Clubs
case object TwoClubs extends Two with Clubs
case object ThreeClubs extends Three with Clubs
case object FourClubs extends Four with Clubs
case object FiveClubs extends Five with Clubs
case object SixClubs extends Six with Clubs
case object SevenClubs extends Seven with Clubs
case object EightClubs extends Eight with Clubs
case object NineClubs extends Nine with Clubs
case object TenClubs extends Ten with Clubs
case object JackClubs extends Jack with Clubs
case object QueenClubs extends Queen with Clubs
case object KingClubs extends King with Clubs

case object AceHearts extends Ace with Hearts
case object TwoHearts extends Two with Hearts
case object ThreeHearts extends Three with Hearts
case object FourHearts extends Four with Hearts
case object FiveHearts extends Five with Hearts
case object SixHearts extends Six with Hearts
case object SevenHearts extends Seven with Hearts
case object EightHearts extends Eight with Hearts
case object NineHearts extends Nine with Hearts
case object TenHearts extends Ten with Hearts
case object JackHearts extends Jack with Hearts
case object QueenHearts extends Queen with Hearts
case object KingHearts extends King with Hearts

object CardTrick {
  def mkWinner(trump: Option[Suit]): Seq[Card] => Card = seq =>
    seq.foldLeft(None: Option[Card]) {
      case (None, c) => Some(c)
      case (Some(c1), c2) if c1.suit == c2.suit && c1.order > c2.order => Some(c1)
      case (Some(c1), c2) if c1.suit == c2.suit || trump.contains(c2.suit) => Some(c2)
      case (Some(c1), _) => Some(c1)
    } get
}
