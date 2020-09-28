package euler

import euler.EulerTools.time

import scala.annotation.tailrec
import scala.language.implicitConversions

object p043 extends App {

  /**
    * https://projecteuler.net/problem=43
    *
    * Sub-string divisibility
    *
    * The number, 1406357289, is a 0 to 9 pandigital number because
    * it is made up of each of the digits 0 to 9 in some order,
    * but it also has a rather interesting sub-string divisibility property.
    *
    * Let d1 be the 1st digit, d2 be the 2nd digit, and so on.
    * In this way, we note the following:
    *
    * d2d3d4=406 is divisible by 2
    * d3d4d5=063 is divisible by 3
    * d4d5d6=635 is divisible by 5
    * d5d6d7=357 is divisible by 7
    * d6d7d8=572 is divisible by 11
    * d7d8d9=728 is divisible by 13
    * d8d9d10=289 is divisible by 17
    *
    * Find the sum of all 0 to 9 pandigital numbers with this property.
    */


  time {

    // Build a 3-digit list from number (only 1st digit is not part of the list)
    // 1406357289 -> List(406, 63, 635, 357, 572, 728, 289)

    def subNumbs(n: Long): List[Long] = {
      @tailrec
      def run(n: Long, acc: List[Long] = Nil): List[Long] =
        if (n < 1000) acc else run(n / 10, (n % 1000) +: acc)

      run(n)
    }

    val pans = (0L to 9L).permutations
    val primes = List(2, 3, 5, 7, 11, 13, 17)

    val validPans = for {
      pan <- pans // pan = Vector(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
      if pan(0) != 0 // leading zero -> not considered a valid pandigital number
      nPan = pan.fold(0L) { (a, b) => 10 * a + b } // build a number from vector
      nums = subNumbs(nPan)
      if nums.zip(primes).forall(p => p._1 % p._2 == 0)
    } yield nPan

    val ans = validPans.sum
    println(s"Answer: $ans")
    assert(ans == 16695334890L, "Expected 16695334890")

  }

}
