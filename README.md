# Comparison-of-Spark-and-Flink

```bash
# TERMINAL 1:
sbt assembly

# TERMINAL 2:
cat access.log  |  nc -lk 9999

# TERMINAL 1:
./bin/spark-submit --class spark.Main ~/Comparison-of-Spark-and-Flink/target/scala-2.11/Comparison-of-Spark-and-Flink-assembly-1.0.jar
```
