package com.nickdferrara.routes

import com.nickdferrara.dto.CredentialDto
import com.nickdferrara.dto.PersonDto
import com.nickdferrara.extension.toCredential
import com.nickdferrara.extension.toDto
import com.nickdferrara.extension.toPerson
import com.nickdferrara.models.Credential
import com.nickdferrara.models.ErrorResponse
import com.nickdferrara.models.Person
import com.nickdferrara.service.CredentialService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.litote.kmongo.coroutine.CoroutineDatabase

fun Route.credentialRoutes(
    database: CoroutineDatabase
) {
    val service = CredentialService(database)

    route("/credential") {
        post {
            val request = call.receive<CredentialDto>()
            val person = request.toCredential()
            service.create(person)
                ?.let { credentialId ->
                    call.response.headers.append("Credential-Id-Header", credentialId.toString())
                    call.respond(HttpStatusCode.Created)
                } ?: call.respond(HttpStatusCode.BadRequest, ErrorResponse.BAD_REQUEST_RESPONSE)
        }

        get {
            val credentialList =
                service.findAll()
                    .map(Credential::toDto)
            call.respond(credentialList)
        }

        get("/{id}") {
            val id = call.parameters["id"].toString()
            service.findById(id)
                ?.let { foundCredential -> call.respond(foundCredential.toDto()) }
                ?: call.respond(HttpStatusCode.NotFound,
                    ErrorResponse.NOT_FOUND_RESPONSE)
        }

        put("/{id}") {
            val id = call.parameters["id"].toString()
            val credentialRequest = call.receive<CredentialDto>()
            val credential = credentialRequest.toCredential()

            val updatedSuccessfully = service.updateById(id, credential)

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