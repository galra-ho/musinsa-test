package com.example.musinsa

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@EnableCaching
@SpringBootApplication
class MusinsaApplication

fun main(args: Array<String>) {
	runApplication<MusinsaApplication>(*args)
}
