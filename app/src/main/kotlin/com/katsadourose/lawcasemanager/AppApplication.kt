package com.katsadourose.lawcasemanager

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["com.katsadourose.lawcasemanager"])
class AppApplication

fun main(args: Array<String>) {
    runApplication<AppApplication>(*args)
}
