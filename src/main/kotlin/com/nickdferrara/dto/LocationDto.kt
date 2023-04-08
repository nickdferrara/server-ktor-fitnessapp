package com.nickdferrara.dto

import kotlinx.serialization.Serializable

@Serializable
data class LocationDto(
    var _id: String? = null,
    val address: AddressDto,
    val description: String,
)
