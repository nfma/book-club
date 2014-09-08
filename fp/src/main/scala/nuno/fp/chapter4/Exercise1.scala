package nuno.fp.chapter4

object Exercise1 {

  sealed trait Option[+A] {
    def flatMap[B](f: A => Option[B]): Option[B] = map(f).getOrElse(None)

    def orElse[B >: A](ob: => Option[B]): Option[B] = map(Some(_)).getOrElse(ob)

    def filter(f: A => Boolean): Option[A] = if (map(f).getOrElse(false)) this else None

    def map[B](f: A => B): Option[B] = this match {
      case Some(a) => Some(f(a))
      case _ => None
    }

    def getOrElse[B >: A](default: => B): B = this match {
      case Some(a) => a
      case _ => default
    }
  }

  case class Some[+A](get: A) extends Option[A]

  case object None extends Option[Nothing]

}
