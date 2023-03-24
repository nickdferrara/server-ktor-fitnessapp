package com.nickdferrara.models

import kotlinx.serialization.Serializable

@Serializable
data class Address(
    val id: Int,
    val street: String,
    val city: String,
    val state: String,
    val zip: Int
)
