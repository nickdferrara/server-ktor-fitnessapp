package com.nickdferrara.models

import kotlinx.serialization.Serializable

@Serializable
data class Coach(
    val id: Int,
    val person: Person,
    val credential: Credential,
    val address: Address
)
