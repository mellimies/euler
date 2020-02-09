package euler

import euler.EulerTools.time

import scala.annotation.tailrec

/**
  * https://projecteuler.net/problem=9
  *
  * Special Pythagorean triplet
  *
  * Problem 9
  * A Pythagorean triplet is a set of three natural numbers, a < b < c, for which,
  *
  * a**2 + b**2 = c**2
  * For example, 3**2 + 4**2 = 9 + 16 = 25 = 5**2.
  *
  * There exists exactly one Pythagorean triplet for which a + b + c = 1000.
  * Find the product abc.
  */

object p009 extends App {

  // https://en.wikipedia.org/wiki/Pythagorean_triple#Generating_a_triple

  def triplet(n: Int, m: Int): List[Int] = {
    // println(s"call: triplet($n, $m)")

    val a = m*m - n*n
    val b = 2*m*n
    val c = m*m + n*n
    List(a, b, c)
  }

  time {
    @tailrec
    def solve9(n: Int, m: Int, nOrg: Int=1, mOrg: Int=2): List[Int] = {
      require(m > n, s"m must be greater than n, was called with n=$n, m=$m")
      val answer = triplet(n, m)

      answer match {
        case s if answer.sum > 1000 =>
          println(s"Sum too large: $s! Continue with ${n+1}.")
          solve9(nOrg+1, mOrg+1, nOrg+1, mOrg+1)
        case _ if answer.sum == 1000 => answer
        case _ => solve9(n, m+1, nOrg, mOrg)
        // does not handle the case when n gets big with no solution :(
        // but luckily the is one without multiplying triplets...
      }

    }

    val ans9 = solve9(1,2)
    println(s"Found triplets: $ans9")
    assert(31875000 == ans9.product)

  }

}
