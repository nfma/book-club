package nuno.fp.chapter6

import nuno.fp.chapter6.Exercise11.{Coin, Machine, Turn, simulateMachine}
import org.scalatest.{Matchers, WordSpec}

class Exercise11Spec extends WordSpec with Matchers {
  "Exercise 11" should {
    "pass rule 1: Inserting a coin into a locked machine will cause it to unlock if there’s any candy left" in {
      simulateMachine(List(Coin)).run(Machine(locked = true, 1, 0)) should be((1, 1), Machine(locked = false, 1, 1))
    }

    "pass rule 2: Turning the knob on an unlocked machine will cause it to dispense candy and become locked" in {
      simulateMachine(List(Turn)).run(Machine(locked = false, 1, 1)) should be((0, 1), Machine(locked = true, 0, 1))
    }

    "pass rule 3: Turning the knob on a locked machine or inserting a coin into an unlocked machine does nothing" in {
      simulateMachine(List(Turn)).run(Machine(locked = true, 1, 0)) should be((1, 0), Machine(locked = true, 1, 0))
      simulateMachine(List(Coin)).run(Machine(locked = false, 1, 1)) should be((1, 1), Machine(locked = false, 1, 1))
    }

    "pass rule 4: A machine that's out of candy ignores all inputs" in {
      simulateMachine(List(Turn)).run(Machine(locked = true, 0, 0)) should be((0, 0), Machine(locked = true, 0, 0))
      simulateMachine(List(Coin)).run(Machine(locked = true, 0, 0)) should be((0, 0), Machine(locked = true, 0, 0))
    }

    "simulate several inputs" in {
      simulateMachine(Nil).run(Machine(locked = true, 2, 0)) should be((2, 0), Machine(locked = true, 2, 0))
      simulateMachine(List(Coin, Turn)).run(Machine(locked = true, 2, 0)) should be((1, 1), Machine(locked = true, 1, 1))
      simulateMachine(List(Turn, Coin, Coin, Turn, Turn)).run(Machine(locked = true, 2, 0)) should be((1, 1), Machine(locked = true, 1, 1))
      simulateMachine(List(Turn, Coin, Coin, Turn, Coin, Turn, Turn, Coin)).run(Machine(locked = true, 2, 0)) should be((0, 2), Machine(locked = true, 0, 2))
    }
  }
}
