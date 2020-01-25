package euler

import EulerTools.time

/**
  * https://projecteuler.net/problem=4
  *
  * Largest palindrome product
  *
  * Problem 4
  * A palindromic number reads the same both ways. The largest palindrome made
  * from the product of two 2-digit numbers is 9009 = 91 × 99.
  *
  * Find the largest palindrome made from the product of two 3-digit numbers.
  */

object p004 extends App {

  time {

    val nums = 999

    // collect palindromes and take max, brute force
    val palindromes = for {
      n1 <- nums to nums / 2 by -1
      n2 <- nums to nums / 2 by -1
      p = n1 * n2
      if p == reverseInt(p) // string or int about the same peformance
    } yield p
    // print(palindromes)
    val maxPalindrome = palindromes.max
    println(maxPalindrome) // Elapsed time: 2221.5755000000004 ms
    assert(maxPalindrome == 906609)
  }

  def reverseInt(n: Int): Int = {
    var result = 0
    var num = n
    while (num > 0) {
      result = result * 10 + num % 10
      num = num / 10
    }
    result
  }

}
