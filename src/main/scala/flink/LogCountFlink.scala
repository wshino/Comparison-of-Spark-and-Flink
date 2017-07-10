package flink

import org.apache.flink.api.java.utils.ParameterTool
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.windowing.time.Time

import scala.util.{Failure, Success, Try}

/**
  * Created by ryoasu on 2017/07/10.
  */
object LogCountFlink extends App {

  // TODO NOT WORK.
  case class AccessLog(api: String, version: String, recommendId: String, targetAndFallback: String, count: Long = 1)

  // the port to connect to
  val port: Int = Try {
    ParameterTool.fromArgs(args).getInt("port")
  } match {
    case Success(v) => v
    case Failure(e) =>
      System.err.println("No port specified. Please run 'APIRequestLogCount --port <port>'")
      1
  }

  // get the execution environment
  val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
  // get input data by connecting to the socket
  val text = env.socketTextStream("localhost", port, '\n')

  val recommendIdCounts = text
    .map { req =>
      val segments = req.split("/").tail.toList
      AccessLog(segments(0), segments(1), segments(2), segments(4))
    }
    .keyBy("recommendId")
    .timeWindow(Time.seconds(5), Time.seconds(1))
    .sum("count")

  recommendIdCounts.print().setParallelism(1)
  env.execute("Count API request log")
}
