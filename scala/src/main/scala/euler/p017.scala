package main.scala.euler

import euler.EulerTools

object p017 extends App {

  /**
    * https://projecteuler.net/problem=17
    *
    * Number letter counts
    *
    * Problem 17
    *
    * If the numbers 1 to 5 are written out in words: one, two, three, four, five,
    * then there are 3 + 3 + 5 + 4 + 4 = 19 letters used in total.
    *
    * If all the numbers from 1 to 1000 (one thousand) inclusive were written out in words,
    * how many letters would be used?
    *
    *
    * NOTE: Do not count spaces or hyphens. For example, 342 (three hundred and forty-two)
    * contains 23 letters and 115 (one hundred and fifteen) contains 20 letters.
    * The use of "and" when writing out numbers is in compliance with British usage.
    */

  import EulerTools.time

  val digits: Map[Int, String] = (1 to 9).zip(List("one", "two", "three", "four", "five",
    "six", "seven", "eight", "nine")).toMap

  val teens: Map[Int, String] = Map(11 -> "eleven", 12 -> "twelve", 13 -> "thirteen", 14 -> "fourteen",
    15 -> "fifteen", 16 -> "sixteen", 17 -> "seventeen", 18 -> "eighteen", 19 -> "nineteen")

  val tens: Map[Int, String] = Map(10 -> "ten", 20 -> "twenty", 30 -> "thirty", 40 -> "forty",
    50 -> "fifty", 60 -> "sixty", 70 -> "seventy", 80 -> "eighty", 90 -> "ninety")

  time {

    import math.Integral.Implicits._

    def numToList(n: Int): List[Int] = {
      def run(n: Int, acc: List[Int] = Nil): List[Int] =
        if (n < 10) n +: acc
        else {
          val (times, modulo) = n /% 10
          run(times, modulo +: acc)
        }

      run(n)
    }

    def numListToWords(l: List[Int]): String = {
      def run(acc: List[String], ls: List[Int]): String = {
        ls match {
          case Nil => acc.filterNot(_ == "").mkString(" ")
          case n :: ns if n == 0 => run(acc, ns)
          case n :: ns =>
            val sep = if (acc.isEmpty || ls.count(_ == 0) == ls.size) "" else "and"
            ls.size match {
              case 4 => run(s"${digits(n)} thousand" +: acc :+ sep, ns)
              case 3 => run(acc :+ sep :+ s"${digits(n)} hundred", ns)
              case 2 =>
                val twoDigits = 10 * n + ns.head
                if (teens.contains(twoDigits)) run(acc :+ sep :+ s"${teens(twoDigits)}", Nil)
                else {
                  val digitStr = if (ns.head == 0) "" else s"-${digits(ns.head)}"
                  run(acc :+ sep :+ s"${tens(n * 10)}$digitStr", Nil)
                }
              case 1 => run(acc :+ sep :+ s"${digits(n)}", ns)
            }
        }
      }
      run(Nil, l)
    }

    val ans = (1 to 1000)
      .map(numToList)
      .map(numListToWords)
      .map(_.replaceAll("[ -]", ""))
      .map(_.length)
      .sum

    println(s"Answer: $ans")
    assert(ans == 21124, "Expected 21124")

  }
}
