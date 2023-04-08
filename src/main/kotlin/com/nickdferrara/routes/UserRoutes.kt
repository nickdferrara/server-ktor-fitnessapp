package com.nickdferrara.routes

import com.nickdferrara.dto.UserDto
import com.nickdferrara.extension.toDto
import com.nickdferrara.extension.toUser
import com.nickdferrara.models.ErrorResponse
import com.nickdferrara.models.User
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
                ?.let { user -> call.respond(user.toDto()) }
        }

        get {
            val userList =
                service.findAll()
                    .map(User::toDto)
            call.respond(userList)
        }

        get("/{id}") {
            val id = call.parameters["id"].toString()
            service.findById(id)
                ?.let { foundUser -> call.respond(foundUser.toDto()) }
                ?: call.respond(HttpStatusCode.NotFound,
                    ErrorResponse.NOT_FOUND_RESPONSE)
        }

        put("/{id}") {
            val id = call.parameters["id"].toString()
            val userRequest = call.receive<UserDto>()
            val user = userRequest.toUser()

            val updatedSuccessfully = service.updateById(id, user)

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