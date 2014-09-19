package paul.fp.chapter4

object Exercise4 {

  def sequence[A](a: List[Option[A]]): Option[List[A]] = a match {
    case Nil => Some(Nil)
    case h::t => sequence(t).map (l => h match {
      case None => l
      case Some(v) => v::l
    })
  }
}




