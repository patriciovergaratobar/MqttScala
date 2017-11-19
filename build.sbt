
lazy val commonSettings = Seq(
  version := "0.1-SNAPSHOT",
  organization := "com.pvergara.scala.mqtt",
  scalaVersion := "2.12.4",
  test in assembly := {}
)

lazy val app = (project in file("app")).
  settings(commonSettings: _*).
  settings(
      mainClass in Compile := Some("com.pvergara.scala.mqtt.MqttSubscriberScala"),
      mainClass in assembly := Some("com.pvergara.scala.mqtt.MqttSubscriberScala")
  )


libraryDependencies ++= Seq(
  "org.eclipse.paho" % "mqtt-client" % "0.4.0"
)

resolvers += "MQTT Repository" at "https://repo.eclipse.org/content/repositories/paho-releases/"

