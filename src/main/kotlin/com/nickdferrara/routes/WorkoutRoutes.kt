package com.nickdferrara.routes

import com.nickdferrara.dto.CredentialDto
import com.nickdferrara.dto.WorkoutDto
import com.nickdferrara.extension.toCredential
import com.nickdferrara.extension.toDto
import com.nickdferrara.extension.toWorkout
import com.nickdferrara.models.Credential
import com.nickdferrara.models.ErrorResponse
import com.nickdferrara.models.Workout
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
            val request = call.receive<WorkoutDto>()
            val workout = request.toWorkout()
            service.create(workout)
                ?.let { workout ->
                    call.respond(workout.toDto())
                    call.respond(HttpStatusCode.Created)
                }
        }

        get {
            val workoutList =
                service.findAll()
                    .map(Workout::toDto)
            call.respond(workoutList)
        }

        get("/{id}") {
            val id = call.parameters["id"].toString()
            service.findById(id)
                ?.let { foundWorkout -> call.respond(foundWorkout.toDto()) }
                ?: call.respond(HttpStatusCode.NotFound,
                    ErrorResponse.NOT_FOUND_RESPONSE)
        }

        get("/getbyuser/{id}") {
            val id = call.parameters["id"].toString()
            service.findByUserId(id)
                ?.let { foundWorkout -> call.respond(foundWorkout.toDto()) }
                ?: call.respond(HttpStatusCode.NotFound,
                    ErrorResponse.NOT_FOUND_RESPONSE)
        }

        put("/{id}") {
            val id = call.parameters["id"].toString()
            val workoutRequest = call.receive<WorkoutDto>()
            val workout = workoutRequest.toWorkout()

            val updatedSuccessfully = service.updateById(id, workout)

            if (updatedSuccessfully) {
                call.respond(HttpStatusCode.NoContent)
            } else {
                call.respond(HttpStatusCode.BadRequest,
                    ErrorResponse.BAD_REQUEST_RESPONSE)
            }
        }

        delete("/{id}") {
            val id = call.parameters["id"].toString()

            val deletedSuccessfully = service.deleteById(id)

            if (deletedSuccessfully) {
                call.respond(HttpStatusCode.NoContent)
            } else {
                call.respond(HttpStatusCode.NotFound, ErrorResponse.NOT_FOUND_RESPONSE)
            }
        }
    }

}