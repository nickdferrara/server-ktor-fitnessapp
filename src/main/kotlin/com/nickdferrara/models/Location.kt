package com.nickdferrara.models

import kotlinx.serialization.Serializable

@Serializable
data class Location(
    val id: Int,
    val address: Address,
    val description: String,
)
