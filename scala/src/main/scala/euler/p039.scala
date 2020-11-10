package main.scala.euler

import euler.EulerTools.time

object p039 extends App {

  /**
    * Integer right triangles
    *
    * If p is the perimeter of a right angle triangle with integral length sides, {a,b,c},
    * there are exactly three solutions for p = 120.
    *
    * {20,48,52}, {24,45,51}, {30,40,50}
    *
    * For which value of p ≤ 1000, is the number of solutions maximised?
    */

  time {

    // p = a + b + c, a**2 + b**2 = c**2
    def isValidTriangle(p: Int, a: Int, b: Int): Boolean = p * p - 2 * p * (a + b) + 2 * a * b == 0

    val pLimit = 1000

    val trianglesWithP = for {
      p <- 1 to pLimit
      a <- 1 to p / 3 // a is smallest so has to be at most 1/3 of p
      b <- a + 1 to p * 2 / 3 // b is between a and 2p/3
      if isValidTriangle(p, a, b)
    } yield p -> (a, b)

    val pWithNumSolutions = trianglesWithP.groupBy(_._1).mapValues(_.size) // p -> count(solutions)
    val mostSolutions = pWithNumSolutions.fold((0, 0)) {
      (acc, candidate) => if (candidate._2 > acc._2) (candidate._1, candidate._2) else acc
    }
    val ans = mostSolutions._1
    println(s"P=${mostSolutions._1} has most solutions with count: ${mostSolutions._2}")
    println(s"Answer: $ans")
    assert(ans == 840, "Expected 840")
  }
}
