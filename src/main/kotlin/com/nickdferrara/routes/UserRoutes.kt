package com.nickdferrara.routes

import com.nickdferrara.dto.UserDto
import com.nickdferrara.extension.toUser
import com.nickdferrara.models.ErrorResponse
import com.nickdferrara.service.UserService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.litote.kmongo.coroutine.CoroutineDatabase

fun Route.userRoutes(
    database: CoroutineDatabase
) {
    val service = UserService(database)

    route("/user") {
        post {
            val request = call.receive<UserDto>()
            val user = request.toUser()
            service.create(user)
                ?.let { userId ->
                    call.response.headers.append("My-User-Id-Header", userId.toString())
                    call.respond(HttpStatusCode.Created)
                } ?: call.respond(HttpStatusCode.BadRequest, ErrorResponse.BAD_REQUEST_RESPONSE)
        }
    }

}