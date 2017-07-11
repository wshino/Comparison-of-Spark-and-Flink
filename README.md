# Comparison-of-Spark-and-Flink

Run on MacOS

```bash
brew install apache-spark

# TERMINAL 1:
sbt assembly

# TERMINAL 2:
cat access.log  |  nc -lk 9999

# TERMINAL 1:
./bin/spark-submit --class spark.Main ~/Comparison-of-Spark-and-Flink/target/scala-2.11/Comparison-of-Spark-and-Flink-assembly-1.0.jar
```

```bash
brew install apache-flink

# TERMINAL 1:
sbt assembly

cat access.log | nc -lk 9999 

# TERMINAL 2:
tail -f /usr/local/Cellar/apache-flink/1.3.1/libexec/log/flink-*.out 

# TERMINAL 3:
/usr/local/Cellar/apache-flink/1.3.1/bin/flink run --class flink.LogCountFlink /Users/shinohara-wataru/Documents/github/Comparison-of-Spark-and-Flink/target/scala-2.11/Comparison-of-Spark-and-Flink-assembly-1.0.jar --port 9999
```