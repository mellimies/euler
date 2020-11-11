package main.scala.euler

import euler.EulerTools.time

object p021 extends App {

  /**
    * Amicable numbers
    *
    * Let d(n) be defined as the sum of proper divisors of n
    * (numbers less than n which divide evenly into n).
    * If d(a) = b and d(b) = a, where a ≠ b, then a and b are an amicable pair and
    * each of a and b are called amicable numbers.
    *
    * For example, the proper divisors of 220 are 1, 2, 4, 5, 10, 11, 20, 22, 44, 55 and 110;
    * therefore d(220) = 284. The proper divisors of 284 are 1, 2, 4, 71 and 142; so d(284) = 220.
    *
    * Evaluate the sum of all the amicable numbers under 10000.
    */


  time {

    def properDivisors(n: Int): List[Int] = {
      def run(testNum: Int, acc: List[Int]): List[Int] = {
        //println(s"$testNum -> ${testNum > n / 2}")
        if (testNum > n / 2) acc.reverse
        else if (n % testNum == 0) run(testNum + 1, testNum +: acc)
        else run(testNum + 1, acc)
      }

      run(1, Nil)
    }

    val limit = 10000
    val ns = 1 until limit
    val ans = ns.map(n => (n, properDivisors(n).sum))
      .filter(p => p._1 != p._2) // numbers must be different
      .map(p => p._1 -> properDivisors(p._2).sum) // sum -> divisors -> sum2
      .filter(p => p._1 == p._2) // sum == sum2
      .map(_._1).sum

    println(s"Answer: $ans")
    assert(ans == 31626, "Expected 31626")
  }
}
