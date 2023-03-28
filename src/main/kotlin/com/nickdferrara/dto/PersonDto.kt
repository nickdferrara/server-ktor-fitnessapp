package com.nickdferrara.dto

import kotlinx.serialization.Serializable

@Serializable
data class PersonDto(
    val id: String? = null,
    val firstName: String,
    val lastName: String,
    )
