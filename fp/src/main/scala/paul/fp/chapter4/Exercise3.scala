package paul.fp.chapter4

object Exercise3 {
  def map2[A,B,C](a: Option[A], b: Option[B])(f: (A, B) => C): Option[C] =
    for {
      aa <- a
      bb <- b
    } yield f(aa,bb)

  // or
//  a flatMap (aa => b map (bb => f(aa, bb)))

}


