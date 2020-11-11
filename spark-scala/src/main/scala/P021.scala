import org.apache.spark.sql.SparkSession

/**
  * Amicable numbers
  *
  * Let d(n) be defined as the sum of proper divisors of n
  * (numbers less than n which divide evenly into n).
  * If d(a) = b and d(b) = a, where a ≠ b, then a and b are an amicable pair and
  * each of a and b are called amicable numbers.
  *
  * For example, the proper divisors of 220 are 1, 2, 4, 5, 10, 11, 20, 22, 44, 55 and 110;
  * therefore d(220) = 284. The proper divisors of 284 are 1, 2, 4, 71 and 142; so d(284) = 220.
  *
  * Evaluate the sum of all the amicable numbers under 10000.
  */

object P021 extends App {

  def properDivisors(n: Long): List[Long] = {
    def run(testNum: Long, acc: List[Long]): List[Long] = {
      //println(s"$testNum -> ${testNum > n / 2}")
      if (testNum > n / 2) acc.reverse
      else if (n % testNum == 0) run(testNum + 1, testNum +: acc)
      else run(testNum + 1, acc)
    }

    run(1, Nil)
  }

  val spark = SparkSession.builder()
    .appName("P021")
    .master("local[*]")
    .getOrCreate()

  spark.sparkContext.setLogLevel("WARN")

  import org.apache.spark.sql.functions.{col, expr, sum, udf}

  val properDivisorsUdf = udf(properDivisors(_: Long))

  val limit = 10000


  // n -> divisors -> sum(divs) -> divisors2 -> sum(divs2) -> compare to original

  val ans = spark.range(limit - 1)
    .withColumn("divisors", properDivisorsUdf(col("id")))
    .withColumn("numSum", expr("AGGREGATE(divisors, 0L, (acc, x) -> acc + x)"))
    .filter(expr("id != numSum")) // must be different numbers
    .withColumn("divisors2", properDivisorsUdf(col("numSum")))
    .withColumn("numSum2", expr("AGGREGATE(divisors2, 0L, (acc, x) -> acc + x)"))
    .filter(expr("id == numsum2"))
    .select(sum("id")).first()(0)

  println(s"Answer: $ans")
  assert(ans == 31626, "Expected 31626")

}
