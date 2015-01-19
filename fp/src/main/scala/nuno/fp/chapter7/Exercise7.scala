package nuno.fp.chapter7

object Exercise7 {
  println("""
      | map(y)(id) == y
      |
      | if a: A -> A' and a*: A* ->A'*
      |
      | map(y)(g) == f*(y)                        (A) substituting id for g and using a* as g*
      |
      | map(map(y)(g))(f) == map(g*(y))(f)        applying map on both sides
      |
      | map(map(y)(g))(f) == f*(g*(y))            substituting map for f* as given in (A)
      |
      | map(map(y)(g))(f) == (f* compose g*)(y)   applying function composition, i.e.: f compose g = f(g(x))
      |
      | map(map(y)(g))(f) == (f compose g)*(y)    using the parametricity law, i.e.: a* compose ra = ra' compose a*
      |
      | map(map(y)(g))(f) == map(y)(f compose g)  substituting f* for map as given in (A)
      |
      |
      |"""".stripMargin)
}
