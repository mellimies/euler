package main.scala.euler

import euler.EulerTools.time

object p091 extends App {

  /**
    * Right triangles with integer coordinates
    * The points P (x1, y1) and Q (x2, y2) are plotted at integer co-ordinates and
    * are joined to the origin, O(0,0), to form ΔOPQ.
    *
    * There are exactly fourteen triangles containing a right angle that can be formed
    * when each co-ordinate lies between 0 and 2 inclusive; that is,
    * 0 ≤ x1, y1, x2, y2 ≤ 2.
    *
    * Given that 0 ≤ x1, y1, x2, y2 ≤ 50, how many right triangles can be formed?
    */

  time {

    // Length of triangle sides given points p and q

    def triangleSides(p: (Int, Int), q: (Int, Int)): List[Int] = {
      val (px, py) = p
      val (qx, qy) = q

      val lineP = px * px + py * py
      val lineQ = qx * qx + qy * qy
      val linePQ = (px - qx) * (px - qx) + (py - qy) * (py - qy)

      List(lineP, lineQ, linePQ).sorted.reverse

    }

    def isSquareTriangle(p: (Int, Int), q: (Int, Int)): Boolean = {
      val isSquare = {
        val sides = triangleSides(p, q)
        sides.head == sides.tail.sum
      }
      // println(s"P: $p, Q: $q -> $isSquare")
      isSquare
    }

    //val (xMax, yMax) = (2, 2)
    val (xMax, yMax) = (50, 50)
    val xs = 0 to xMax
    val ys = 0 to yMax

    val points = for {
      x <- xs
      y <- ys
    } yield (x, y)

    val testPoints = points.tail.combinations(2) // exclude (0,0)
    val ans = testPoints.count(p => isSquareTriangle(p(0), p(1)))

    println(s"Answer: $ans")
    assert(ans == 14234, "Expected 14234")
  }
}
