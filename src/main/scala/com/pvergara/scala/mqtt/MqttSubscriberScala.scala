package com.pvergara.scala.mqtt

import org.eclipse.paho.client.mqttv3._
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence

/**
* Created by pvergara.
* Mqtte subscriber to topics
*/
object MqttSubscriberScala {

  def main(args: Array[String]) {
    val topics = Array("cl/topicoA", "com/topicoA", "com/topicoB")
    val persistence = new MemoryPersistence
    val clientMqtt = new MqttClient("tcp://localhost:1883", "SubscriptorEventScala", persistence)
    //Open Connection
    clientMqtt.connect()
    //subscriber list topic
    clientMqtt.subscribe(topics)
    val callback = new MqttCallback {
      override def messageArrived(topic: String, message: MqttMessage): Unit = {
        println("Topic : %s, Message : %s".format(topic, message))
      }
      override def connectionLost(cause: Throwable): Unit = println("Connection Lost by %s".format(cause))
      override def deliveryComplete(token: IMqttDeliveryToken): Unit = { }
    }
    clientMqtt.setCallback(callback)
  }

}
