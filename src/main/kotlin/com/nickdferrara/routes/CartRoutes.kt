package com.nickdferrara.routes

import com.nickdferrara.dto.CartDto
import com.nickdferrara.extension.toCart
import com.nickdferrara.extension.toDto
import com.nickdferrara.models.ErrorResponse
import com.nickdferrara.service.CartService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.litote.kmongo.coroutine.CoroutineDatabase

fun Route.cartRoutes(
    database: CoroutineDatabase
) {
    val service = CartService(database)

    route("/cart") {
        post {
            val request = call.receive<CartDto>()
            val cart = request.toCart()
            service.create(cart)
                ?.let { cart ->
                    call.respond(cart.toDto())
                }
        }

        get("/{id}") {
            val id = call.parameters["id"].toString()
            service.findById(id)
                ?.let { foundCart -> call.respond(foundCart.toDto()) }
                ?: call.respond(
                    HttpStatusCode.NotFound,
                    ErrorResponse.NOT_FOUND_RESPONSE)
        }

        put("/{id}") {
            val id = call.parameters["id"].toString()
            val cartRequest = call.receive<CartDto>()
            val cart = cartRequest.toCart()

            val updatedSuccessfully = service.updateById(id, cart)

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