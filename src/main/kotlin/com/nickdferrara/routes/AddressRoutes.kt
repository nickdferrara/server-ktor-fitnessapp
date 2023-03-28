package com.nickdferrara.routes

import com.nickdferrara.dto.AddressDto
import com.nickdferrara.extension.toAddress
import com.nickdferrara.models.ErrorResponse
import com.nickdferrara.service.AddressService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.litote.kmongo.coroutine.CoroutineDatabase

fun Route.addressRoutes(
    database: CoroutineDatabase
) {
    val service = AddressService(database)

    route("/address") {
        post {
            val request = call.receive<AddressDto>()
            val address = request.toAddress()
            service.create(address)
                ?.let { addressId ->
                    call.response.headers.append("My-User-Id-Header", addressId.toString())
                    call.respond(HttpStatusCode.Created)
                }?: call.respond(HttpStatusCode.BadRequest, ErrorResponse.BAD_REQUEST_RESPONSE)
        }
    }
}

