package main.scala.euler

import euler.EulerTools.time

/**
  * https://projecteuler.net/problem=6
  *
  * Sum square difference
  *
  * Problem 6
  *
  * The sum of the squares of the first ten natural numbers is,
  *
  * 1**2 + 2**2 +...+ 10**2 = 385
  *
  * The square of the sum of the first ten natural numbers is,
  *
  * (1+2+...+10)**2 = 55**2 = 3025
  *
  * Hence the difference between the sum of the squares of the first ten natural numbers
  * and the square of the sum is 3025 − 385 = 2640.
  *
  * Find the difference between the sum of the squares of the first one hundred natural numbers
  * and the square of the sum.
  */

object p006 extends App {

  time {

    // Closed form: https://brilliant.org/wiki/sum-of-n-n2-or-n3/
    def sumOfSquares(n: Int): Int = n * (n + 1) * (2 * n + 1) / 6

    def squareOfSum(n: Int): Int = {
      val num = n * (n + 1) / 2 // Einstein's summation
      num * num
    }

    assert(squareOfSum(10) - sumOfSquares(10) == 2640)

    val ans = squareOfSum(100) - sumOfSquares(100)
    println(s"ANS $ans")
    assert(ans == 25164150)

  }
}