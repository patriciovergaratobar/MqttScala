package com.pvergara.scala.mqtt.utils

import com.pvergara.scala.mqtt.model.Broker
import org.eclipse.paho.client.mqttv3.MqttClient
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence

/**
  * Created by pvergara on 11-11-17.
  */
object ConnectBroker {

  def getMqttConection(broker: Broker) : MqttClient = {
    val persistence = new MemoryPersistence
    val clientMqtt = new MqttClient(broker.hostBroker, broker.identifier, persistence)
    clientMqtt.connect
    clientMqtt
  }

}
