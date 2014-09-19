package paul.fp.chapter4
import paul.fp.chapter4.Exercise6.{Either, Right, Left}
object Exercise7 {

  def sequence[E, A](es: List[Either[E, A]]): Either[E, List[A]] = es match {

    case Nil => Right(Nil)
    case h::t => h match {
      case Left(e) => Left(e)
      case Right(a) => sequence(t).flatMap(list => h match {
        case Left(e) => Left(e)
        case Right(a) => Right(a :: list)
      })
    }
  }

}
