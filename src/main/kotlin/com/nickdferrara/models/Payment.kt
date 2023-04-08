package com.nickdferrara.models

import java.util.Currency

data class Payment(
    val amount: Long,
    val currency: String = "usd"
)
