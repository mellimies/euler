package euler

object EulerTools {

  // source?
  def time[R](block: => R): R = {
    val t0 = System.nanoTime()
    val result = block // call-by-name
    val t1 = System.nanoTime()
    println("Elapsed time: " + (t1 - t0) * 10e-6 + " ms")
    result
  }

  case class NumberToPrimeFactors(n: Int, ps: List[Int])

  def primeFactors(n: Int): NumberToPrimeFactors = {
    val primes = List(2, 3, 5, 7, 11, 13, 17, 19)
    // just to have some validation for input
    require(primes.max > 0.8 * n, s"Input too large for primeFactors: $n")

    def factorLoop(num: Int, primeList: List[Int], acc: List[Int]): NumberToPrimeFactors = {
      primeList match {
        case List() => throw new Error("primeFactors.factorLoop run out of primes!")
        case p :: ps =>
          if (num <= 2 || (primes contains num)) NumberToPrimeFactors(n, num :: acc)
          else if (num % p == 0) factorLoop(num / p, p :: ps, p :: acc) // success
          else factorLoop(num, ps, acc) // next prime
      }

    }
    val pList = factorLoop(n, primes, List())
    //println(s"$n -> $pList")
    pList
  }
}
