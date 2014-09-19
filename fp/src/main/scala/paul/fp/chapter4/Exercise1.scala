package paul.fp.chapter4

object Exercise1 {

  sealed trait Option[+A] {

    def map[B](f: A => B): Option[B] = this match {
      case Some(value) => Some(f(value))
      case _ => None
    }

    def flatMap[B](f: A => Option[B]): Option[B] = map(f) getOrElse None

    def getOrElse[B >: A](default: => B): B = this match {
      case None => default
      case Some(value) => value
    }

    def orElse[B >: A](ob: => Option[B]): Option[B] = map(Some(_)) getOrElse ob

    def filter(f: A => Boolean): Option[A] = map { z =>
      if (f(z)) Some(z) else None
    } getOrElse None

  }

  case class Some[+A](get: A) extends Option[A]

  case object None extends Option[Nothing]

}
