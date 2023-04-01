package com.nickdferrara.plugins

import com.nickdferrara.routes.*
import io.ktor.server.routing.*
import io.ktor.server.http.content.*
import io.ktor.server.application.*
import org.litote.kmongo.coroutine.CoroutineDatabase

fun Application.configureRouting(
    database: CoroutineDatabase
) {
    routing {
        userRoutes(database)
        static("/static") {
            resources("static")
        }
    }
}
