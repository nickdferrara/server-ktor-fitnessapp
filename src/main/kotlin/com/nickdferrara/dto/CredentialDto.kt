package com.nickdferrara.dto

import kotlinx.serialization.Serializable

@Serializable
data class CredentialDto (
    val id: String? = null,
    val email: String,
    val password: String,
)