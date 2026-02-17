package org.quotes.ancients

import org.apache.juli.logging.Log
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DemoApplication

private val logger = LoggerFactory.getLogger(DemoApplication::class.java)

fun main(args: Array<String>) {
    runApplication<DemoApplication>(*args)
    logger.warn("System is up and running")
}
