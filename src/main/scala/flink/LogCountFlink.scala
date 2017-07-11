package flink

import org.apache.flink.api.java.utils.ParameterTool
import org.apache.flink.streaming.api.scala._

/**
  * Created by ryoasu on 2017/07/10.
  */
object LogCountFlink extends App {

  var port: Int = 0

  case class AccessLog(api: String, version: String, recommendId: String, targetAndFallback: String)

  case class AccessPrintLog(recommendId: String, count: Long = 1)

  // the port to connect to
  try {
    port = ParameterTool.fromArgs(args).getInt("port")
  } catch {
    case e: Exception => {
      System.err.println("No port specified. Please run 'APIRequestLogCount --port <port>'")
    }
  }


  // get the execution environment
  val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
  // get input data by connecting to the socket
  val text = env.socketTextStream("localhost", port, '\n')

  val recommendIdCounts = text
    .map { req =>
      val segments = req.split("/").tail.toList
      val target = segments.size match {
        case x if x > 3 => segments(3)
        case _ => ""
      }
      AccessLog(segments(0), segments(1), segments(2), target)
    }
    .map(x => AccessPrintLog(x.recommendId))
    .keyBy("recommendId")
    .sum("count")

  recommendIdCounts.print().setParallelism(1)

  env.execute("Count API request log")
}
