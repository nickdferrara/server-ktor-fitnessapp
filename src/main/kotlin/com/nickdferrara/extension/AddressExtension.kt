package com.nickdferrara.extension

import com.nickdferrara.dto.AddressDto
import com.nickdferrara.models.Address

fun Address.toDto(): AddressDto =
    AddressDto(
        id = this.id.toString(),
        street = this.street,
        city = this.city,
        state = this.state,
        zip = this.zip
    )

fun AddressDto.toAddress(): Address =
    Address(
        street = this.street,
        city = this.city,
        state = this.state,
        zip = this.zip
    )

