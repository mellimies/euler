package euler

import euler.EulerTools.time

/**
  * https://projecteuler.net/problem=22
  * Names scores
  *
  * Problem 22
  * Using names.txt (right click and 'Save Link/Target As...'),
  * a 46K text file containing over five-thousand first names,
  * begin by sorting it into alphabetical order.
  * Then working out the alphabetical value for each name,
  * multiply this value by its alphabetical position in the list
  * to obtain a name score.
  *
  * For example, when the list is sorted into alphabetical order,
  * COLIN, which is worth 3 + 15 + 12 + 9 + 14 = 53,
  * is the 938th name in the list.
  * So, COLIN would obtain a score of 938 × 53 = 49714.
  *
  * What is the total of all the name scores in the file?
  */

object p022 extends App {

  time {

    // single comma-delimited line with double-quotes around values,
    // path is correct at least from IntelliJ IDE

    val source = io.Source.fromFile("../data/p022_names.txt", "UTF-8")
    val input = try source.getLines()
      .toList
      .head
      .replaceAll("\"", "") // drop quotes from values
      .split(",")
      .toList
      .sorted
    finally source.close()

    assert("COLIN" == input(937))

    val wordValues = for {
      (word, index) <- input.zipWithIndex
      charSum = word.toList.map(n => n.toInt - 64).sum
    } yield (index + 1) * charSum

    val answer = wordValues.sum
    println(answer)
    assert(answer == 871198282)
  }

}
