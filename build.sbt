name := "Comparison-of-Spark-and-Flink"

version := "1.0"

scalaVersion := "2.11.7"

val flinkVersion = "1.3.1"

val sparkVersion = "2.1.0"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % sparkVersion % "provided",
  "org.apache.spark" %% "spark-mllib" % sparkVersion % "provided",
//  "org.apache.spark" %% "spark-graphx" % "2.1.0" % "provided",
  "org.apache.spark" %% "spark-hive" % "2.1.0" % "provided",
//  "graphframes" % "graphframes" % "0.5.0-spark2.1-s_2.11",
  "org.scalatest" %% "scalatest" % "2.2.4" % "test",
  "org.scalacheck" %% "scalacheck" % "1.12.2" % "test",
  "org.slf4j" % "slf4j-api" % "1.7.10",
  "org.apache.flink" %% "flink-scala"           % flinkVersion % "provided",
  "org.apache.flink" %% "flink-streaming-scala" % flinkVersion % "provided"
)
