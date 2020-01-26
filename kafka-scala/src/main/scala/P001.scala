/**
  * https://projecteuler.net/problem=1
  *
  * Multiples of 3 and 5
  *
  * Problem 1
  * If we list all the natural numbers below 10 that are multiples of 3 or 5,
  * we get 3, 5, 6 and 9. The sum of these multiples is 23.
  *
  * Find the sum of all the multiples of 3 or 5 below 1000.
  */

import java.util.Properties

import org.apache.kafka.common.serialization.{IntegerDeserializer, IntegerSerializer}
import org.apache.kafka.streams.scala.StreamsBuilder
import org.apache.kafka.streams.{StreamsConfig, Topology, TopologyTestDriver}
import org.scalatest.matchers.should.Matchers

object P001 extends App with Matchers {

  import org.apache.kafka.streams.scala.ImplicitConversions._
  import org.apache.kafka.streams.scala.Serdes._

  val topicIn = "P001"
  val topicOut = s"$topicIn-out"
  val builder: StreamsBuilder = new StreamsBuilder()

  // build the topology to filter and sum
  builder.stream[Int, Int](topicIn)
    .filter((_, v) => v % 3 == 0 || v % 5 == 0)
    .groupByKey
    .reduce(_ + _)
    .toStream
    .through(topicOut)

  val config = new Properties()
  // If used in state store (grouped), applicationID cannot contain spaces
  config.setProperty(StreamsConfig.APPLICATION_ID_CONFIG, topicIn)
  config.setProperty(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "topology_under_test:1000")

  val topology = builder.build(config)

  val answer10 = getAnswer(topology, 1 until 10)
  answer10 shouldBe 23

  val answer1000 = getAnswer(topology, 1 until 1000)
  answer1000 shouldBe 233168

  def getAnswer(t: Topology, r: Range): Int = {
    val testDriver = new TopologyTestDriver(builder.build(config), config)

    val intSerializer = new IntegerSerializer
    val intDeserializer = new IntegerDeserializer

    val inputTopic = testDriver.createInputTopic(topicIn, intSerializer, intSerializer)
    val outputTopic = testDriver.createOutputTopic(topicOut, intDeserializer, intDeserializer)

    r foreach { n: Int => inputTopic.pipeInput(0, n) } // Produce input

    val outputs = outputTopic.readValuesToList() // Read complete output (to java List) and get last value
    val answer = outputs.get(outputs.size() - 1)
    println("*** " + answer)

    testDriver.close() // must be there, if not then old input is not cleaned
    answer

  }
}
