package io.orbeon.wrapper

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class OrbeonWrapperApplication

fun main(args: Array<String>) {
    runApplication<OrbeonWrapperApplication>(*args)
}
