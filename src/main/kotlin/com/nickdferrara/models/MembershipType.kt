package com.nickdferrara.models

import kotlinx.serialization.Serializable

@Serializable
data class MembershipType(
    val id: Int,
    val description: String,
    val isReoccuring: Boolean,
)
