package euler

import euler.EulerTools.{NumberToPrimeFactors, primeFactors, time}

/**
  * https://projecteuler.net/problem=5
  *
  * Smallest multiple
  *
  * Problem 5
  * 2520 is the smallest number that can be divided by each of the numbers from 1 to 10
  * without any remainder.
  *
  * What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20?
  */

/**
  * It's unreal how many different approaches I have used trying to solve this and how much time
  * I've spent. But here it finally is, actually pretty proud about myself: did not give up and
  * also was able to determine the solution on my own. And now that I think of it it actually makes
  * a lot of sense (reduce input sequence to list of prime factors and keep enough powers for each
  * primes so that division by input is even.)
  *
  * Attending the EPFL Scala functional programming course the second time helped a lot (a few
  * years ago it was way too difficult.)
  */

object p005 extends App {

  time {

    case class PrimeToPower(n: Int, p: Int) {
      def value: Int = math.pow(n, p).toInt
    }

    val limit = 20
    val allNums: List[Int] = (1 to limit).toList

    // Compute product of primes-to-power in map and check for solution
    def isSolution(primeMap: Map[Int, PrimeToPower]): (Boolean, Int) = {
      val currentProduct = primeMap.mapValues(_.value).values.product
      val sol = allNums.map(n => currentProduct % n).forall(_ == 0)
      (sol, currentProduct)
    }

    // Compute updates to accumulator: if acc holds a key for prime, compare
    // power in stored prime-to-power and if it's not large enough, return
    // a new instance with larger power. If there's no key yet, use current instance.

    def updatesForNumber(n: NumberToPrimeFactors, acc: Map[Int, PrimeToPower]): List[(Int, PrimeToPower)] = {
      val primeCounts = n.ps.groupBy(identity).mapValues(l => l.length)
        .map(elem => PrimeToPower(elem._1, elem._2))
      val accUpdates = primeCounts.map(p2p => {
        val prime = p2p.n
        val oldValue = acc.getOrElse(prime, p2p)
        if (p2p.p > oldValue.p) (prime, p2p) else (prime, oldValue)
      })
      accUpdates.toList
    }

    val primeFactorsList = allNums.map(primeFactors)

    def loop(num2Primes: List[NumberToPrimeFactors], acc: Map[Int, PrimeToPower]): Int = {
      val (foundSolution, currentProduct) = isSolution(acc)
      if (foundSolution) currentProduct
      else
        num2Primes match {
          case List() => throw new Error("loop run out of primes before finding solution!")
          case x :: xs => loop(xs, acc ++ updatesForNumber(x, acc))
        }
    }

    val ans = loop(primeFactorsList, Map.empty)
    println("ANS " + ans)
    assert(ans == 232792560)

  }
}