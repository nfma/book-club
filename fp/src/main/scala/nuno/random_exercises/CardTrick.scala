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

sealed trait CardRank {
  def order: Int
}

case object Ace extends CardRank {
  val order = 13
}
case object Two extends CardRank {
  val order = 1
}
case object Three extends CardRank {
  val order = 2
}
case object Four extends CardRank {
  val order = 3
}
case object Five extends CardRank {
  val order = 4
}
case object Six extends CardRank {
  val order = 5
}
case object Seven extends CardRank {
  val order = 6
}
case object Eight extends CardRank {
  val order = 7
}
case object Nine extends CardRank {
  val order = 8
}
case object Ten extends CardRank {
  val order = 9
}
case object Jack extends CardRank {
  val order = 10
}
case object Queen extends CardRank {
  val order = 11
}
case object King extends CardRank {
  val order = 12
}

case class Card(rank: CardRank, suit: Suit)

object CardTrick {
  def mkWinner(trump: Option[Suit]): Seq[Card] => Card = seq =>
    seq.foldLeft(None: Option[Card]) {
      case (None, c) => Some(c)
      case (Some(c1), c2) if c1.suit == c2.suit && c1.rank.order > c2.rank.order => Some(c1)
      case (Some(c1), c2) if c1.suit == c2.suit || trump.contains(c2.suit) => Some(c2)
      case (Some(c1), _) => Some(c1)
    } get
}
