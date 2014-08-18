package misterflibble.fp.chapter2

object Ex5 {
  def compose[A,B,C](f: B => C, g: A => B): A => C = (a:A) => f(g(a))
 }
