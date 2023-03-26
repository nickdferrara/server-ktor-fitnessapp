package com.nickdferrara

import io.ktor.server.application.*
import com.nickdferrara.plugins.*

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused")
fun Application.module() {
    configureSecurity()
    configureMonitoring()
    configureSerialization()
    configureRouting(configureDatabase())
}
