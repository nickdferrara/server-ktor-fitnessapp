package com.nickdferrara.routes

import com.nickdferrara.dto.ProductDto
import com.nickdferrara.dto.UserDto
import com.nickdferrara.extension.toDto
import com.nickdferrara.extension.toProduct
import com.nickdferrara.extension.toUser
import com.nickdferrara.models.ErrorResponse
import com.nickdferrara.service.ProductService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.litote.kmongo.coroutine.CoroutineDatabase

fun Route.productRoutes(
    database: CoroutineDatabase
){
    val service = ProductService(database)

    route("/product") {
        post {
            val request = call.receive<ProductDto>()
            val product = request.toProduct()
            service.create(product)
                ?.let { user -> call.respond(product.toDto()) }
        }

        get("/{id}") {
            val id = call.parameters["id"].toString()
            service.findById(id)
                ?.let { foundProduct -> call.respond(foundProduct.toDto()) }
                ?: call.respond(
                    HttpStatusCode.NotFound,
                    ErrorResponse.NOT_FOUND_RESPONSE)
        }

        put("/{id}") {
            val id = call.parameters["id"].toString()
            val productRequest = call.receive<ProductDto>()
            val product = productRequest.toProduct()

            val updatedSuccessfully = service.updateById(id, product)

            if (updatedSuccessfully) {
                call.respond(HttpStatusCode.NoContent)
            } else {
                call.respond(HttpStatusCode.BadRequest,
                    ErrorResponse.BAD_REQUEST_RESPONSE)
            }
        }
    }

}