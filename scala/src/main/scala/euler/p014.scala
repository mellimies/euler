package euler

import EulerTools.time

/**
  * https://projecteuler.net/problem=14
  *
  * Longest Collatz sequence
  *
  * Problem 14
  * The following iterative sequence is defined for the set of positive integers:
  *
  * n → n/2 (n is even)
  * n → 3n + 1 (n is odd)
  *
  * Using the rule above and starting with 13, we generate the following sequence:
  *
  * 13 → 40 → 20 → 10 → 5 → 16 → 8 → 4 → 2 → 1
  * It can be seen that this sequence (starting at 13 and finishing at 1) contains 10 terms.
  * Although it has not been proved yet (Collatz Problem), it is thought that all starting numbers
  * finish at 1.
  *
  * Which starting number, under one million, produces the longest chain?
  *
  * NOTE: Once the chain starts the terms are allowed to go above one million.
  */

object p014 extends App {

  // once again got tripped by Int not being enough, collatz returned negative
  // values and it was a never-ending story

  def collatz(num: Long): Long = num match {
    case n if n < 0 => throw new RuntimeException(s"Got a negative number $n, should never happen!")
    case n if n % 2 == 0 => n / 2
    case n => 3 * n + 1
  }

  def getChain(n: Long, nums: List[Long] = Nil): List[Long] = {
    n match {
      case 1 => (n :: nums).reverse
      case n => getChain(collatz(n), n :: nums)
    }
  }

  time { // builds the whole list and takes max
    val nums = 1 until 1000000
    val vals = for {
      n <- nums
      chainSize = getChain(n).size
    } yield (n -> chainSize)
    println(vals.maxBy(_._2))
  }

  time { // compare just two results, no big list in memory
    val limit = 1000000

    @annotation.tailrec
    def maxPair(currentTuple: (Int, Int), winner:(Int, Int)): (Int, Int) = {
      currentTuple._1 match {
        case n if n == limit - 1 => println("DONE " + currentTuple); winner
        case n => {
          val newN = n + 1
          val newSize = getChain(newN).size
          val newTuple = newN -> newSize
          val newWinner = if(winner._2 > newSize) winner else newTuple
          maxPair(newTuple, newWinner)
        }
      }
    }

    // Elapsed time: 28460.0884 ms (lol, about 5 secs with stopwatch)
    println(maxPair((1 -> getChain(1).size), (0, 0)))

  }

}
