package com.nickdferrara.routes

import com.nickdferrara.dto.LocationDto
import com.nickdferrara.extension.toDto
import com.nickdferrara.extension.toLocation
import com.nickdferrara.service.LocationService
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.litote.kmongo.coroutine.CoroutineDatabase

fun Route.locationRoutes(
    database: CoroutineDatabase
) {
    val service = LocationService(database)

    route("/location") {
        post {
            val request = call.receive<LocationDto>()
            val location = request.toLocation()
            service.create(location)
                ?.let { location ->
                    call.respond(location.toDto())
                }
        }
    }

}