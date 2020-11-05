package main.scala.euler

import euler.EulerTools.time

object p145 extends App {

  /**
    * How many reversible numbers are there below one-billion?
    *
    * Some positive integers n have the property that the sum [ n + reverse(n) ]
    * consists entirely of odd (decimal) digits. For instance, 36 + 63 = 99 and 409 + 904 = 1313.
    * We will call such numbers reversible; so 36, 63, 409, and 904 are reversible.
    * Leading zeroes are not allowed in either n or reverse(n).
    *
    * There are 120 reversible numbers below one-thousand.
    *
    * How many reversible numbers are there below one-billion (10**9)?
    */

  time {
    import math.Integral.Implicits._

    def numToList(n: Long): List[Long] = {
      def run(n: Long, acc: List[Long] = Nil): List[Long] =
        if (n < 10) n +: acc
        else {
          val (times, modulo) = n /% 10
          run(times, modulo +: acc)
        }

      run(n)
    }

    def reversed(n: Long): Option[Long] = {
      if (n % 10 == 0) None
      else Some(numToList(n).reverse.foldLeft(0L)((a, b) => 10 * a + b))
      //else Some(numToList(n).foldRight(0L)((a, b) => a + 10 * b)) // not faster than reverse + left?
    }

    val goodNums = (1 until 1000000000).foldLeft(Set[Long]()) { (acc, n) =>
      if (acc.contains(n)) acc
      else {
        val nReversed = reversed(n)
        if (nReversed.isEmpty) acc
        else {
          val n2 = nReversed.get
          if (numToList(n + n2).map(_ % 2).forall(_ == 1)) acc ++ List(n, n2) else acc
        }
      }
    }

    val ans = goodNums.size
    println(s"Answer $ans")
    assert(ans == 608720, "Expected 608720")

    // Runtime is about 12 minutes, one core busy.
    // Parallel range did not improve performance so probably wasn't using it correctly.
    // Should learn to profile the code too...

  }

}
