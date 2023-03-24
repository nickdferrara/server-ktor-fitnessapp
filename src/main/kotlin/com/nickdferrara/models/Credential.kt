package com.nickdferrara.models

import kotlinx.serialization.Serializable

@Serializable
data class Credential(
    val id: Int,
    val email: String,
    val password: String,
)
