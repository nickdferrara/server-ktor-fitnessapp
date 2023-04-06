package com.nickdferrara.routes

import com.nickdferrara.dto.WorkoutDto
import com.nickdferrara.extension.toWorkout
import com.nickdferrara.models.ErrorResponse
import com.nickdferrara.service.WorkoutService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.litote.kmongo.coroutine.CoroutineDatabase
import java.time.LocalDateTime

fun Route.workoutRoutes(
    database: CoroutineDatabase
) {
    val service = WorkoutService(database)

    route("/workout") {
        post {
            println("----------------------------------------------------------------------------------------------")
            println(LocalDateTime.now())
            println("----------------------------------------------------------------------------------------------")
            val request = call.receive<WorkoutDto>()
            val workout = request.toWorkout()
            service.create(workout)
                ?.let { workoutId ->
                    call.response.headers.append("My-User-Id-Header", workoutId.toString())
                    call.respond(HttpStatusCode.Created)
                } ?: call.respond(HttpStatusCode.BadRequest, ErrorResponse.BAD_REQUEST_RESPONSE)
        }
    }

}