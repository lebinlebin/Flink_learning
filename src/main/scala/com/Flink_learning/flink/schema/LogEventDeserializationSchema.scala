package com.Flink_learning.flink.schema

import com.Flink_learning.flink.bean.LogEvent
import org.apache.flink.api.common.typeinfo.TypeInformation
import org.apache.flink.streaming.util.serialization.KeyedDeserializationSchema
import org.json4s.DefaultFormats
import org.json4s.native.JsonMethods._

/**
  * Created by liulebin on 3/6/18.
  */
class LogEventDeserializationSchema  extends KeyedDeserializationSchema[LogEvent]{
  override def isEndOfStream(t: LogEvent): Boolean = false

  override def deserialize(messageKey: Array[Byte], message: Array[Byte], topic: String, partition: Int, offset: Long): LogEvent = {
    val json = parse(new String(message))
    implicit val formats = DefaultFormats
    json.extract[LogEvent]
  }

  override def getProducedType: TypeInformation[LogEvent] = TypeInformation.of(LogEvent.getClass.asInstanceOf[Class[LogEvent]])
}
