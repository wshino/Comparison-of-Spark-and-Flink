package spark

import org.apache.spark.sql.types.StructType

/**
  * Created by wshino on 2017/07/05.
  */
object Main extends App {

  import org.apache.spark.sql.SparkSession

  val spark = SparkSession.builder.appName("Count UUID").getOrCreate()

  import spark.implicits._

  val accessLogSchema = new StructType()
    .add("api", "string")
    .add("version", "string")
    .add("recommendId", "string")
    .add("targetAndFallback", "string")

  case class AccessLog(api: String, version: String, recommendId: String, targetAndFallback: String)

  val lines = spark.readStream.format("socket")
    .option("host", "localhost")
    .option("port", 9999)
    .option("sep", "/")
    .schema(accessLogSchema)
    .load()

  val df = lines.as[String].map(x => x.split("/")(3)).toDF().withColumnRenamed("value", "recommendId")
  val count = df.groupBy("recommendId").count()
  val query = count.writeStream.outputMode("complete").format("console").start()
  query.awaitTermination()
}
