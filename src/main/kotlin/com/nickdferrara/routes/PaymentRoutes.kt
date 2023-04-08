package com.nickdferrara.routes

import com.nickdferrara.dto.PaymentDto
import com.nickdferrara.dto.UserDto
import com.nickdferrara.extension.toPayment
import com.stripe.model.PaymentIntent
import com.stripe.param.PaymentIntentCreateParams
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.paymentRoutes()
{
    route("/payments") {
        post {
            val request = call.receive<PaymentDto>()
            val payment = request.toPayment()

            val params: PaymentIntentCreateParams = PaymentIntentCreateParams.builder()
                .setAmount(payment.amount)
                .setCurrency(payment.currency)
                .build()

            val paymentIntent: PaymentIntent = PaymentIntent.create(params)
            call.respond(paymentIntent.clientSecret)
        }
    }
}