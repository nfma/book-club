package nuno.fp.chapter6

import nuno.fp.chapter6.Exercise10.State
import nuno.fp.chapter6.Exercise10.State.{get, set}

object Exercise11 {

  sealed trait Input

  case object Coin extends Input

  case object Turn extends Input

  case class Machine(locked: Boolean, candies: Int, coins: Int)

  def simulateMachine(inputs: List[Input]): State[Machine, (Int, Int)] = inputs match {
    case Nil => get[Machine].map(m => (m.candies, m.coins))
    case Coin :: t => for {
      m <- get[Machine]
      _ <- if (m.locked && m.candies > 0) set(m.copy(locked = false, coins = m.coins + 1)) else set(m)
      r <- simulateMachine(t)
    } yield r
    case Turn :: t => for {
      m <- get[Machine]
      _ <- if (m.locked) set(m) else set(m.copy(locked = true, candies = m.candies - 1))
      r <- simulateMachine(t)
    } yield r
  }
}
