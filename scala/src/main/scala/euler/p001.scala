package euler

import EulerTools.time

/**
  * https://projecteuler.net/problem=1
  *
  * Multiples of 3 and 5
  *
  * Problem 1
  * If we list all the natural numbers below 10 that are multiples of 3 or 5,
  * we get 3, 5, 6 and 9. The sum of these multiples is 23.
  *
  * Find the sum of all the multiples of 3 or 5 below 1000.
  */


object p001 extends App {

  val limit = 1000 // below 1000

  // loop every number until limit and take modulo

  time {
    val nums = for {
      i <- 1 until limit
      if i % 3 == 0 || i % 5 == 0
    } yield i
    val answer = nums.sum
    println(answer) // Elapsed time: 1224.11724 ms
    assert(answer == 233168)
  }

  // another approach, process only relevant numbers

  time {
    val threes = 3 until limit by 3
    val fives = 5 until limit by 5
    val nums = (threes ++ fives).distinct
    val answer = nums.sum
    println(answer) // Elapsed time: 240.46220000000002 ms
    assert(answer == 233168)
  }

}
