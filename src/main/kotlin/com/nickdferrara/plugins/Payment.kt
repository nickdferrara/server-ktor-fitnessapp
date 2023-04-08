package com.nickdferrara.plugins

import com.stripe.Stripe
import io.ktor.server.application.*

fun Application.configurePayments() {
    val stripeSecretKey = System.getenv("STRIPE_SECRET_KEY")
    Stripe.apiKey= stripeSecretKey
}