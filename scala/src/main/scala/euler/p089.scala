package main.scala.euler

import euler.EulerTools.time

import scala.language.implicitConversions

object p089 extends App {

  /**
    * For a number written in Roman numerals to be considered valid there are basic rules
    * which must be followed. Even though the rules allow some numbers to be expressed
    * in more than one way there is always a "best" way of writing a particular number.
    *
    * For example, it would appear that there are at least six ways of writing the number sixteen:
    *
    * IIIIIIIIIIIIIIII
    * VIIIIIIIIIII
    * VVIIIIII
    * XIIIIII
    * VVVI
    * XVI
    *
    * However, according to the rules only XIIIIII and XVI are valid, and the last example
    * is considered to be the most efficient, as it uses the least number of numerals.
    *
    * The 11K text file, roman.txt (right click and 'Save Link/Target As...'), contains
    * one thousand numbers written in valid, but not necessarily minimal, Roman numerals;
    * see About... Roman Numerals for the definitive rules for this problem.
    *
    * Find the number of characters saved by writing each of these in their minimal form.
    *
    * Note: You can assume that all the Roman numerals in the file contain no more than
    * four consecutive identical units.
    */

  time {

    import scala.annotation.tailrec
    val numbers = List(1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1)
    val numerals = List("M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I")
    val numeralToInt = (numerals zip numbers).toMap
    val intToNumeral = (numbers zip numerals).toMap

    class RomanNumeralParser extends util.parsing.combinator.RegexParsers {

      val numeralPair: Parser[Int] = "CD|CM|XC|XL|IX|IV".r ^^ {
        numeralToInt(_)
      }

      val numeral: Parser[Int] = "[MDCLXVI]".r ^^ {
        numeralToInt(_)
      }

      def numerals: Parser[Int] = (numeralPair | numeral) ~ rep(numerals) ^^ {
        case n ~ Nil => n
        case n ~ e => n + e.sum
      }
    }

    def toRomanNumeral(n: Int): String = {
      @tailrec
      def run(n: Int, acc: List[String] = Nil, romans: List[Int] = numbers): String = {
        if (n == 0) acc.reverse.mkString
        else { // recursively find largest numeral that evenly divides number
          val largestDivisorIndex = romans.map(n / _).zipWithIndex.dropWhile(_._1 == 0).head._2
          val largest = romans(largestDivisorIndex)
          val times = n / largest
          run(n - times * largest, intToNumeral(largest) * times +: acc, romans.tail)
        }
      }

      run(n)
    }

    case class Euler89Numeral(numerals: String, value: Int) {

      def savings: Int = numerals.length - toRomanNumeral(value).length

    }

    val parser = new RomanNumeralParser

    val romans = io.Source.fromFile("data/p089_roman.txt")
    try {
      val input = romans.getLines().toList
      val ns = input.map { l => Euler89Numeral(l, parser.parseAll(parser.numerals, l).get) }
      val ans = ns.map(_.savings).sum
      println(s"Savings=$ans")
      assert(ans == 743, "Expected 743")
    }
    finally {
      romans.close()
    }

  }

}
