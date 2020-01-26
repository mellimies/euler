import org.apache.spark.sql.SparkSession

object P001 {
  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder()
      .appName("P001")
      .master("local[*]")
      .getOrCreate()

    spark.sparkContext.setLogLevel("WARN")

    def doSum(limit: Int)= {
      val numsDF = spark.sqlContext.range(1, limit) // DF with col 'id'

      //numsDF.show()
      val sumDF = numsDF
        .filter("(id % 3 == 0) or (id % 5 == 0)")
        .agg("id" -> "sum")

      sumDF.show(false)
      sumDF
    }

    val answer10 = doSum(10).first()
    println(s"Answer for 10 should be 23, is: $answer10")

    val answer1000 = doSum(1000)
    println(s"Answer for 1000 is: $answer1000")

  }
}
