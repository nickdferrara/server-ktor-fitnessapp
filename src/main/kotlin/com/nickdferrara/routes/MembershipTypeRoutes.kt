package com.nickdferrara.routes

import com.nickdferrara.dto.MembershipTypeDto
import com.nickdferrara.extension.toDto
import com.nickdferrara.extension.toMembershipType
import com.nickdferrara.models.ErrorResponse
import com.nickdferrara.models.MembershipType
import com.nickdferrara.service.MembershipTypeService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.litote.kmongo.coroutine.CoroutineDatabase

fun Route.membershipTypeRoutes(
    database: CoroutineDatabase
) {
    val service = MembershipTypeService(database)

    route("/membershiptype") {
        post {
            val request = call.receive<MembershipTypeDto>()
            val membershipType = request.toMembershipType()
            service.create(membershipType)
                ?.let { membershipTypeId ->
                    call.response.headers.append("My-User-Id-Header", membershipTypeId.toString())
                    call.respond(HttpStatusCode.Created)
                } ?: call.respond(HttpStatusCode.BadRequest, ErrorResponse.BAD_REQUEST_RESPONSE)
        }

        get {
            val membershipTypeList =
                service.findAll()
                    .map(MembershipType::toDto)
            call.respond(membershipTypeList)
        }

        get("/{id}") {
            val id = call.parameters["id"].toString()
            service.findById(id)
                ?.let { foundMembershipType -> call.respond(foundMembershipType.toDto()) }
                ?: call.respond(HttpStatusCode.NotFound, ErrorResponse.NOT_FOUND_RESPONSE)
        }

        put("/{id}") {
            val id = call.parameters["id"].toString()
            val membershipTypeRequest = call.receive<MembershipTypeDto>()
            val membershipType = membershipTypeRequest.toMembershipType()
            val updatedSuccessfully = service.updateById(id, membershipType)

            if (updatedSuccessfully) {
                call.respond(HttpStatusCode.NoContent)
            } else {
                call.respond(HttpStatusCode.BadRequest, ErrorResponse.BAD_REQUEST_RESPONSE)
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