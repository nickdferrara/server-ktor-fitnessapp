package com.nickdferrara.dto

import kotlinx.serialization.Serializable

@Serializable
data class LocationDto(
    val id: String? = null,
    val address: AddressDto,
    val description: String,
)
