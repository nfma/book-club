package nuno.fp.chapter6

import nuno.fp.chapter6.Exercise1.RNG

object Exercise10 {

  case class State[S, +A](run: S => (A, S)) {
    def map[B](f: A => B): State[S, B] = State(s => {
      val (a, s1) = run(s)
      (f(a), s1)
    })

    def map2[B, C](s: State[S, B])(f: (A, B) => C): State[S, C] = State(st => {
      val (a, s1) = run(st)
      val (b, s2) = s.run(s1)
      (f(a, b), s2)
    })

    def flatMap[B](f: A => State[S, B]): State[S, B] = State(s => {
      val (a, s1) = run(s)
      f(a).run(s1)
    })
  }

  object State {
    def unit[S, A](a: A): State[S, A] = State((s: S) => (a, s))

    def sequence[S, A](fs: List[State[S, A]]): State[S, List[A]] = fs match {
      case Nil => State.unit(Nil)
      case h :: t => h.map2(sequence(t)) {_ :: _}
    }

    def get[S]: State[S, S] = State(s => (s, s))

    def set[S](s: S): State[S, Unit] = State(_ => ((), s))
  }

  type Rand[A] = State[RNG, A]
}
