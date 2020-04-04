package euler

import euler.EulerTools.{primeFactors, time}

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
  * I've spent. But here it finally is, actually pretty proud of myself: did not give up and
  * also was able to determine the solution on my own. And now that I think of it it actually makes
  * a lot of sense (reduce input sequence to list of prime factors and keep enough powers for each
  * primes so that division by input is even.)
  *
  * Second version of solution drops optimization by checking for solution after processing each
  * number of input list. Instead get prime factors of ever number in input and then for each
  * prime take the highest power and multiply those to get solution.
  *
  * Attending the EPFL Scala functional programming course the second time helped a lot (a few
  * years ago it was way too difficult.)
  */

object p005 extends App {

  time {

    case class PrimeToPower(n: Int, power: Int) {
      def value: Int = math.pow(n, power).toInt
    }

    val limit = 20
    val allNums: List[Int] = (1 to limit).toList

    val primeFactorsList = allNums.map(primeFactors)

    val ans = (
      for {
        primeList <- primeFactorsList.map(np => np.primes) // NumberToPrimeFactors(10,List(5, 2)) => List(5, 2)
        ps = primeList.groupBy(identity) // 10 -> Map(2 -> List(2), 5 -> List(5)))
        primeToPower = ps.map(elem => PrimeToPower(elem._1, elem._2.length))
      } yield primeToPower // for 10: List(PrimeToPower(2,1), PrimeToPower(5,1)))
      )
      .flatten // single list of PrimeToPower instances
      .groupBy(_.n) // collect by prime: 3 -> List(PrimeToPower(3,1), PrimeToPower(3,1), PrimeToPower(3,2)))
      .mapValues(l => l.map(_.power)) // pull power out into list
      .mapValues(_.max) // get highest power from list
      .map(pair => PrimeToPower(pair._1, pair._2).value) // new instance with highest power, expand
      .product // take product for solution

    println("ANS " + ans)
    assert(ans == 232792560)

  }
}