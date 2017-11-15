package com.pvergara.scala.mqtt

import com.pvergara.scala.mqtt.model.Broker
import com.pvergara.scala.mqtt.utils.BrokerConnection
import org.eclipse.paho.client.mqttv3.{MqttClient, _}

/**
* Created by pvergara.
* Mqtte subscriber to topics
*/
object MqttSubscriberScala {

  def main(args: Array[String]) {

    val broker = new Broker("tcp://localhost:1883", "SubscriptorEventScala")

    val conn = new BrokerConnection

    val mqttConexion: MqttClient = conn.getMqttConection(broker)

    val topics = Array("cl/topicoA", "com/topicoA", "com/topicoB")

    mqttListener(mqttConexion, topics)

    mqttPublisher(mqttConexion, "publish this msg :)")

  }

  def mqttListener(mqttConexion: MqttClient, topics: Array[String]): Unit = {
    //subscriber list topic
    mqttConexion.subscribe(topics)
    val callback = new MqttCallback {
      override def messageArrived(topic: String, message: MqttMessage): Unit = {
        println("Topic : %s, Message : %s".format(topic, message))
      }
      override def connectionLost(cause: Throwable): Unit = println("Connection Lost by %s".format(cause))
      override def deliveryComplete(token: IMqttDeliveryToken): Unit = { }
    }
    mqttConexion.setCallback(callback)
  }

  def mqttPublisher(mqttConexion: MqttClient, messageSend: String): Unit = {
    try {
      val topicMqtt = mqttConexion.getTopic("cl/topicoA")
      val msg = new MqttMessage(messageSend.getBytes("utf-8"))
      topicMqtt.publish(msg)
    } catch {
      case e: MqttException => println("Exception : " + e)
    }
  }

}
