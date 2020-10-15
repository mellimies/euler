package main.scala.euler

import euler.EulerTools.time

object p096 extends App {


  time {

    def rowFor(pos: (Int, Int), grid: List[List[Int]]): List[Int] = {
      val (row, _) = pos
      grid(row)
    }

    def colFor(pos: (Int, Int), grid: List[List[Int]]): List[Int] = {
      val (_, col) = pos
      grid
        .flatten // single sequence for whole problem
        .drop(col) // drop until we're at col
        .sliding(1, grid.size) // slide at col by sudoku size
        .flatten
        .toList
    }

    def blockFor(pos: (Int, Int), grid: List[List[Int]]): List[Int] = {
      val (row, col) = pos
      val blockSize = math.sqrt(grid.size).toInt

      // integer division so we get the correct start row and col for block
      val (rowStart, colStart) = (row / blockSize, col / blockSize)
      grid
        .slice(rowStart * blockSize, blockSize * (rowStart + 1))
        .flatMap(r => r.slice(colStart * blockSize, blockSize * (colStart + 1)))
    }

    def possibleNums(pos: (Int, Int), grid: List[List[Int]]): List[Int] = {

      val (row, col) = pos
      val allNums = (0 to 9).toSet

      val usedNums =
        (rowFor((row, col), grid) ++ colFor((row, col), grid) ++ blockFor((row, col), grid)).toSet

      val nums = allNums.diff(usedNums).toList
      //println(s"NUMS $nums for ($row, $col)")
      nums

    }

    // What positions need to be filled
    def puzzleZeros(p: Sudoku): List[(Int, Int)] =
      p.grid
        .zipWithIndex
        .map(p => (p._2, zerosAtRow(p._1)))
        .flatMap(p => p._2.map((p._1, _)))

    def zerosAtRow(ns: List[Int]): List[Int] = ns.zipWithIndex.filter(_._1 == 0).map(_._2)

    case class NumberAtPos(n: Int, pos: (Int, Int))

    case class Sudoku(input: String) {

      def this(grid: List[List[Int]]) {
        this(grid.map(l => l.mkString).mkString("\n"))
      }

      val size: Int = input.split("\n")(0).length
      val blockSize: Int = math.sqrt(size).toInt

      def grid: List[List[Int]] = input.stripMargin.split("\n")
        .map(s => s.map(_.toInt - 48).toList).toList

      // We have some known updates so replace zeros with those at correct positions
      // and return a string in original format as the new problem. Also works with
      // List[Int], TODO <-

      def updateWith(updates: List[NumberAtPos]): String = {
        val newPuzzle = updates.foldLeft(input.stripMargin)((acc, update) => {
          val realIndex = update.pos._1 * (size + 1) + update.pos._2 // account for newlines -> +1
          acc.patch(realIndex, update.n.toString, 1)
        })
        newPuzzle
      }

      // Pretty-print puzzle
      override def toString: String = {

        def rowToString(ns: List[Int]): String =
          (for {
            (n, i) <- ns.zipWithIndex
            sep = if (i % blockSize == blockSize - 1) " " else ""
          } yield n + sep).mkString

        def rowListToString(ss: List[String]): String =
          (for {
            (s, i) <- ss.zipWithIndex
            sep = if (i % blockSize == blockSize - 1) "\n" else ""
          } yield s + sep).mkString(start = "\n", sep = "\n", end = "\n")

        rowListToString(grid.map(l => rowToString(l)))
      }
    } // end Sudoku

    sealed trait PuzzleUpdate
    case class DirectReplacement(ns: List[NumberAtPos]) extends PuzzleUpdate
    case class ByConstraint(ns: List[NumberAtPos]) extends PuzzleUpdate
    case class GuessAndTest(ns: List[NumberAtPos]) extends PuzzleUpdate
    case object NoSolution extends PuzzleUpdate

    def getPuzzleUpdate(sudoku: Sudoku): PuzzleUpdate = {
      val zeros = puzzleZeros(sudoku)
      val candidates = for {
        pos <- zeros // (row, col) for unfilled positions
        ns = possibleNums(pos, sudoku.grid)
      } yield (ns, pos)

      val candidatesByCount = candidates.groupBy(_._1.size)

      // Count: 0 -> no candidates found for position, no solution
      //        1 -> direct assignment, no other digit works
      //       >1 -> digit can possibly be determined by checking other rows/cols (ByConstraint) or
      //             must guess and test

      val multipleOptions = candidates.filter(_._1.size > 1)

      val byConstraint = for { // if candidate is found just once in a block it can be placed
        opt <- multipleOptions
        block = blockFor(opt._2, sudoku.grid)
        inSameBlock = multipleOptions.filter(elem => blockFor(elem._2, sudoku.grid) == block)
        num <- opt._1
        numCount = inSameBlock.count(elem => elem._1.contains(num))
        if numCount == 1
      } yield (List(num), opt._2)

      val updates = if (candidatesByCount.contains(0))
        NoSolution
      else if (candidatesByCount.contains(1))
      // TODO: instead of taking one full list could be taken but it can lead tu duplicates ->
      // should be validated first and return NoSolution for duplicates
        DirectReplacement(candidatesByCount(1).map(elem => NumberAtPos(elem._1.head, elem._2)).take(1))
      else if (byConstraint.nonEmpty)
        ByConstraint(byConstraint.map(elem => NumberAtPos(elem._1.head, elem._2)))
      else {
        val testCandidates = candidatesByCount(candidatesByCount.keys.min).head
        GuessAndTest(testCandidates._1.map(n => NumberAtPos(n, testCandidates._2)))
      }
      updates
    }

    def solve(sudoku: Sudoku, iterationCount: Int = 0): Option[List[List[Int]]] = {
      val zeros = puzzleZeros(sudoku)
      //println(s"Sudoku with $iterationCount iterations and #zeros: ${zeros.size}:\n$sudoku")
      if (zeros.isEmpty) {
        println(s"Done with $iterationCount iterations:\n$sudoku")
        val ns = sudoku.grid.flatten
        assert((1 to sudoku.size).map(e => ns.count(_ == e)).forall(_ == sudoku.size), "Uneven count of numbers in solution?")
        Some(sudoku.grid)
      }
      else {
        if (iterationCount > sudoku.size * sudoku.size) throw new Error("never ending story?")
        val update = getPuzzleUpdate(sudoku)
        //println(s"Updating with: $update")
        update match {
          case NoSolution =>
            //println(s"No solution with $iterationCount: ${sudoku.grid}")
            None
          case dr: DirectReplacement =>
            solve(Sudoku(sudoku.updateWith(dr.ns)), iterationCount + 1)
          case bc: ByConstraint =>
            solve(Sudoku(sudoku.updateWith(bc.ns)), iterationCount + 1)
          case gat: GuessAndTest =>
            val searchSolution = gat.ns.flatMap(elem => {
              val update = sudoku.updateWith(List(elem))
              solve(Sudoku(update), iterationCount + 1)
            })
            Some(searchSolution.flatten)
        }
      }
    }

    // assume current working dir is repository root

    val puzzleFile = io.Source.fromFile("data/p096_sudoku.txt")
    try {
      val puzzleInput = puzzleFile.getLines().filterNot(_.startsWith("Grid")).toList
      val size = puzzleInput.head.length
      val puzzles = puzzleInput.sliding(size, size).map(l => l.mkString("\n")).toList
      val solutions = puzzles.drop(0).flatMap(puzzle => solve(Sudoku(puzzle)))
      val topLefts = solutions.map(ls => ls.head.take(3))
        .map(ns => ns.foldLeft(0) { (acc, n) => 10 * acc + n })
      val ans = topLefts.sum
      println("ANS " + topLefts.sum)
      assert(ans == 24702, "Expected 24702")

    }
    finally {
      puzzleFile.close()
    }

  }
}
