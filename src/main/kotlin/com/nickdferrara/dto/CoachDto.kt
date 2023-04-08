package com.nickdferrara.dto

import kotlinx.serialization.Serializable

@Serializable
data class CoachDto(
    var _id: String? = null,
    val isActive: Boolean,
    val person: PersonDto,
    val credential: CredentialDto,
    val address: AddressDto,
    val profileImage: String?
)
