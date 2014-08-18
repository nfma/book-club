package nuno.fp.chapter4

import nuno.fp.chapter2.Exercise5.compose
import nuno.fp.chapter4.Exercise1.{None, Option, Some}

object Exercise2 {
  def mean(xs: Seq[Double]): Option[Double] =
    if (xs.isEmpty) None
    else Some(xs.sum / xs.length)

  def variance(xs: Seq[Double]): Option[Double] = mean(xs).flatMap(compose(mean, intermediate(xs)))

  private def intermediate(xs: Seq[Double]): (Double) => Seq[Double] = mean => xs.map(v => math.pow(v - mean, 2))
}
