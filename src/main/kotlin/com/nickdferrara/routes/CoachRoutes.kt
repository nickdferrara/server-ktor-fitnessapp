package com.nickdferrara.routes

import com.nickdferrara.dto.CoachDto
import com.nickdferrara.extension.toCoach
import com.nickdferrara.models.ErrorResponse
import com.nickdferrara.service.CoachService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.litote.kmongo.coroutine.CoroutineDatabase

fun Route.coachRoutes(
    database: CoroutineDatabase
) {
    val service = CoachService(database)

    route("/coach") {
        post {
            val request = call.receive<CoachDto>()
            val coach = request.toCoach()
            service.create(coach)
                ?.let { coachId ->
                    call.response.headers.append("My-User-Id-Header", coachId.toString())
                    call.respond(HttpStatusCode.Created)
                } ?: call.respond(HttpStatusCode.BadRequest, ErrorResponse.BAD_REQUEST_RESPONSE)
        }
    }
}