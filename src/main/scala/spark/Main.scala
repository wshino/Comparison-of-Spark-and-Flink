package spark

import org.apache.spark.sql.SparkSession

/**
  * Created by shinohara-wataru on 2017/07/05.
  */
class Main {

  val spark = SparkSession
    .builder()
    .appName("Spark SQL basic example")
    .config("spark.some.config.option", "some-value")
    .getOrCreate()

  // For implicit conversions like converting RDDs to DataFrames
  import spark.implicits._


}
