package main.scala.euler

import euler.EulerTools.time

object p112 extends App {

  /**
    * Bouncy numbers
    *
    * Working from left-to-right if no digit is exceeded by the digit to its left it
    * is called an increasing number; for example, 134468.
    *
    * Similarly if no digit is exceeded by the digit to its right it is called
    * a decreasing number; for example, 66420.
    *
    * We shall call a positive integer that is neither increasing nor decreasing
    * a "bouncy" number; for example, 155349.
    *
    * Clearly there cannot be any bouncy numbers below one-hundred, but just over half
    * of the numbers below one-thousand (525) are bouncy. In fact, the least number
    * for which the proportion of bouncy numbers first reaches 50% is 538.
    *
    * Surprisingly, bouncy numbers become more and more common and by the time we reach
    * 21780 the proportion of bouncy numbers is equal to 90%.
    *
    * Find the least number for which the proportion of bouncy numbers is exactly 99%.
    */

  time {

    import math.Integral.Implicits._

    def numToList(n: Int): List[Int] = {
      def run(n: Int, acc: List[Int] = Nil): List[Int] =
        if (n < 10) n +: acc
        else {
          val (times, modulo) = n /% 10
          run(times, modulo +: acc)
        }

      run(n)
    }

    def isBouncy(n: Int): Boolean = {
      val ns = numToList(n)
      val isIncreasing = ns == ns.sorted
      val nsReversed = ns.reverse
      val isDecreasing = nsReversed == nsReversed.sorted
      !(isIncreasing || isDecreasing)
    }

    def findNumber(targetPercentage: Int): Int = {
      def run(n: Int, bouncyNumCount: Int = 0): Int = {
        val newBouncyNumCount = if (isBouncy(n)) bouncyNumCount + 1 else bouncyNumCount
        if (100 * newBouncyNumCount / n == targetPercentage) n
        else run(n + 1, newBouncyNumCount)
      }

      run(100)
    }

    val ans = findNumber(99)
    println(s"Answer: $ans")
    assert(ans == 1587000, "Expected 1587000")
  }
}
