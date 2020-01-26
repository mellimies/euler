name := "kafka-scala"

version := "0.1"

scalaVersion := "2.12.7"

// https://mvnrepository.com/artifact/org.apache.kafka/kafka-streams
libraryDependencies += "org.apache.kafka" % "kafka-streams" % "2.4.0"

// https://mvnrepository.com/artifact/org.apache.kafka/kafka-streams-scala
libraryDependencies += "org.apache.kafka" %% "kafka-streams-scala" % "2.4.0"

// do not include "Test" configuration in order to access
// test classes directly in src instead of separating code to src and test to test
libraryDependencies += "org.apache.kafka" % "kafka-streams-test-utils" % "2.4.0"

libraryDependencies += "org.scalactic" %% "scalactic" % "3.1.0"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.1.0"
