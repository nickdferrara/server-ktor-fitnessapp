package com.nickdferrara.dto

import kotlinx.serialization.Serializable

@Serializable
data class AddressDto(
    val id: String? = null,
    val street: String,
    val city: String,
    val state: String,
    val zip: Int
)
