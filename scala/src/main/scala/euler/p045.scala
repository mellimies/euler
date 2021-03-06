package euler

import euler.EulerTools.time

object p045 extends App {

  /**
    * https://projecteuler.net/problem=45
    *
    * Triangular, pentagonal, and hexagonal
    *
    * Problem 45
    * Triangle, pentagonal, and hexagonal numbers are generated by the following formulae:
    *
    * Triangle	 	Tn=n(n+1)/2	 	1, 3, 6, 10, 15, ...
    * Pentagonal	Pn=n(3n−1)/2	1, 5, 12, 22, 35, ...
    * Hexagonal	 	Hn=n(2n−1)	 	1, 6, 15, 28, 45, ...
    * It can be verified that T285 = P165 = H143 = 40755.
    *
    * Find the next triangle number that is also pentagonal and hexagonal.
    */

  time {

    def isSolution(a: Double, b: Double)(c: Long): Boolean = {
      // Find the positive root for quadratic equation:
      // Hexagonal number provides c and if it's also a triangle and pentagonal
      // then it must also be integer root value for both.

      val positiveRoot = (-b + math.sqrt(b * b + 4 * a * c)) / (2 * a)
      positiveRoot == positiveRoot.toInt
    }

    val isTriangle = isSolution(0.5, 0.5)_
    val isPentagon = isSolution(1.5, -0.5)_

    assert(isTriangle(40755) == isPentagon(40755), "Sample number validation failed")

    val hexStream: Stream[Long] = {
      def loop(v: Long): Stream[Long] = v #:: loop(v + 1)
      loop(144L)
    }

    val answerStream = for {
      n <- hexStream
      h = n * (2 * n - 1)
      if isPentagon(h)
      if isTriangle(h)
    } yield h

    val answer = answerStream.head
    println(answer)
    assert(answer == 1533776805L)

  }

}
