package com.nickdferrara.extension

import com.nickdferrara.dto.PaymentDto
import com.nickdferrara.models.Payment

fun Payment.toDto(): PaymentDto =
    PaymentDto(
        amount = this.amount,
        currency = this.currency
    )

fun PaymentDto.toPayment(): Payment =
    Payment(
        amount = this.amount,
        currency = this.currency
    )