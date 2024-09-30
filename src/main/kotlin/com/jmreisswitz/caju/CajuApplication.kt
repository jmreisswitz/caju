package com.jmreisswitz.caju

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class CajuApplication

fun main(args: Array<String>) {
	runApplication<CajuApplication>(*args)
}
