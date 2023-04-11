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
        coachRoutes(database)
        workoutRoutes(database)
        locationRoutes(database)
        productRoutes(database)
        paymentRoutes()
        static("/static") {
            resources("static")
        }
    }
}
