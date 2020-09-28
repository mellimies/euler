package main.scala.euler

import euler.EulerTools.time

import scala.language.implicitConversions

object p040 extends App {

  /**
    * https://projecteuler.net/problem=40
    *
    * Champernowne's constant
    *
    * An irrational decimal fraction is created by concatenating the positive integers:
    *
    * 0.123456789101112131415161718192021...
    *
    * It can be seen that the 12th digit of the fractional part is 1.
    *
    * If dn represents the nth digit of the fractional part, find the value
    * of the following expression.
    *
    * d1 × d10 × d100 × d1000 × d10000 × d100000 × d1000000
    */

  // Split a number to a list of numbers, e.g. 123 -> List(1,2,3)
  def num2list(num: Int): List[Int] = {
    import math.Integral.Implicits._
    def run(num: Int): List[Int] = num match {
      case n if n < 10 => List(n)
      case _ => val (div, mod) = num /% 10
        List(mod) ++ run(div)
    }

    val numList = run(num)
    numList.reverse
  }

  def pow10(n: Int): Int = List.fill(n)(10).product // n = 0 -> product = 1

  time {

    import math.Integral.Implicits._
    def digitAt(pos: Int): Int = {

      def getDigitsInNumber(pos: Int): Int = pos match {
        case n if n <= endPosition(1) => 1 // 1-9, positions 1-9
        case n if n <= endPosition(2) => 2 // 10-99, positions 10-189 (9 + 2 * 90)
        case n if n <= endPosition(3) => 3 // 100-999, positions 190-2889 (189 + 3 * 900)
        case n if n <= endPosition(4) => 4 // 1000-9999, positions 2890-38889 (2889 + 4 * 9000)
        case n if n <= endPosition(5) => 5 // 10000-99999, positions 38890-488889 (38889 + 5 * 90000)
        case n if n <= endPosition(6) => 6 // 100000-999999, positions 488890-5888889 (488889 + 6 * 900000)
        case _ => throw new IllegalArgumentException(s"Position too large for getDigitsInNumber: $pos")
      }

      // Get last position of numbers with n digits. E.g. n=1 -> 9, n=2 -> 9 + 2 * 90 = 189
      def endPosition(n: Int): Int =
        List.fill(n)(9)
          .zipWithIndex
          .map(p => 9 * (p._2 + 1) * pow10(p._2))
          .sum

      pos match {
        case n if n <= 9 => n // single digit range -> same as position
        case n =>
          val digits = getDigitsInNumber(n)
          val upFromNumber = pow10(digits - 1) - 1
          val upFromPosition = endPosition(digits - 1)
          val (toAdd, remainder) = (pos - upFromPosition) /% digits
          // if remainder == 0 -> we're at last digit of this number
          val (num, index) = if (remainder == 0) (upFromNumber + toAdd, digits - 1)
          else (upFromNumber + toAdd + 1, remainder - 1)
          num2list(num)(index)
      }
    }


    val ans = digitAt(1) *
      digitAt(10) *
      digitAt(100) *
      digitAt(1000) *
      digitAt(10000) *
      digitAt(100000) *
      digitAt(1000000)

    println(ans)
    assert(ans == 210, "Expected 210")

  }

}
