package ben.fp.chapter6

trait RNG {
  def nextInt: (Int, RNG)
}

object RNG {

  type Rand[+A] = RNG => (A, RNG)

  case class SimpleRNG(seed: Long) extends RNG {
    def nextInt: (Int, RNG) = {
      val newSeed = (seed * 0x5DEECE66DL + 0xBL) & 0xFFFFFFFFFFFFL
      val nextRNG = SimpleRNG(newSeed)
      val n = (newSeed >>> 16).toInt
      (n, nextRNG)
    }
  }

  def nonNegativeInt(rng: RNG): (Int, RNG) = {
    rng.nextInt match {
      case (i, gen) if i == Int.MinValue => (0, gen)
      case (i, gen) => (Math.abs(i), gen)
    }
  }

  def double(rng: RNG): (Double, RNG) = {
    val (i, r) = nonNegativeInt(rng)
    (i / (Int.MaxValue.toDouble + 1), r)
  }

  def intDouble(rng: RNG): ((Int,Double), RNG) = {
    val (int, gen ) = rng.nextInt
    val (dub, gen2) = double(rng)
    (int -> dub) -> gen2
  }
  
  def doubleInt(rng: RNG): ((Double,Int), RNG) = {
    val ((i , d), g) = intDouble(rng)
    (d -> i ) -> g
  }

  def double3(rng: RNG): ((Double,Double,Double), RNG) = {
    val (dub1, gen1) = double(rng)
    val (dub2, gen2) = double(gen1)
    val (dub3, gen3) = double(gen2)
    (dub1, dub2, dub3) -> gen3
  }

  def ints(count: Int)(rng: RNG): (List[Int], RNG) = {
    (1 to count).foldLeft(List.empty[Int] -> rng) {
      case ((intList, rg), i) =>
        val (int, gen) = nonNegativeInt(rg)
        (int +: intList , gen)
    }
  }

  def unit[A](a: A): Rand[A] = (rng:RNG) => (a, rng)

  def double2(rng: RNG): (Double, RNG) = map(nonNegativeInt)( _ / Int.MaxValue.toDouble)(rng)

  def map[A,B](s: Rand[A])(f: A => B): Rand[B] = rng => {
    val (a, rng2) = s(rng)
    (f(a), rng2)
  }

  def map2[A,B,C](ra: Rand[A], rb: Rand[B])(f: (A, B) => C): Rand[C] = ???
}
