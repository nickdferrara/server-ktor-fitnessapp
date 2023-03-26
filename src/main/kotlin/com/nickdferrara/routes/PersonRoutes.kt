package com.nickdferrara.routes

import com.nickdferrara.dto.PersonDto
import com.nickdferrara.extension.toDto
import com.nickdferrara.extension.toPerson
import com.nickdferrara.models.ErrorResponse
import com.nickdferrara.models.Person
import com.nickdferrara.service.PersonService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.litote.kmongo.coroutine.CoroutineDatabase

fun Route.personRoutes(
    database: CoroutineDatabase
) {
    val service = PersonService(database)

    route("/person") {
        post {
            val request = call.receive<PersonDto>()
            val person = request.toPerson()
            service.create(person)
                ?.let { userId ->
                    call.response.headers.append("My-User-Id-Header", userId.toString())
                    call.respond(HttpStatusCode.Created)
                } ?: call.respond(HttpStatusCode.BadRequest, ErrorResponse.BAD_REQUEST_RESPONSE)
        }

        get {
            val peopleList =
                service.findAll()
                    .map(Person::toDto)
            call.respond(peopleList)
        }

        get("/{id}") {
            val id = call.parameters["id"].toString()
            service.findById(id)
                ?.let { foundPerson -> call.respond(foundPerson.toDto()) }
                ?: call.respond(HttpStatusCode.NotFound, ErrorResponse.NOT_FOUND_RESPONSE)
        }
    }
}