package com.nickdferrara.dto

import kotlinx.serialization.Serializable

@Serializable
data class PaymentDto(
    val amount: Long,
    val currency: String = "usd"
)
