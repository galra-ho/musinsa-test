package com.example.musinsa

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
class MusinsaApplication

fun main(args: Array<String>) {
	runApplication<MusinsaApplication>(*args)
}
