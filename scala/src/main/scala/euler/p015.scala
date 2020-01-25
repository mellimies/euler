package euler

import EulerTools.time

object p015 extends App {

  /**
    * https://projecteuler.net/problem=15
    *
    * Lattice paths
    *
    * Problem 15
    * Starting in the top left corner of a 2×2 grid, and only being able to move to the right and down, there are exactly 6 routes to the bottom right corner.
    *
    *
    * How many such routes are there through a 20×20 grid?
    */

  // I was never able to solve this in 2017 but would imagine recursion does the trick.
  // TODO: complete... better algorithm needed, 17x17 about 30 s, 10x10 1 s
  // and replace var?

  time {
    var answer: Long = 0

    def getPaths(rows: Int, cols: Int): Unit = {
      val problemSize = (rows, cols)
      //    println(problemSize)
      problemSize match {
        case (0, 0) => answer += 1
        case (rs, cs) if rs == 0 => /* println(1); */ getPaths(0, cs - 1)
        case (rs, cs) if cs == 0 => /* println(2); */ getPaths(rs - 1, 0)
        case (rs, cs) if rs > 0 => /* println(3); */ getPaths(rs - 1, cs); getPaths(rs, cs - 1)
        case (rs, cs) if cs > 0 => /* println(4); */ getPaths(rs, cs - 1); getPaths(rs - 1, cs)
      }
    }

    getPaths(10, 10)
    println(answer)

  }
}
