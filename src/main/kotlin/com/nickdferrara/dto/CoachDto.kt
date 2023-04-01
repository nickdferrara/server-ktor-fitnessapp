package com.nickdferrara.dto

data class CoachDto(
    val id: String? = null,
    val isActive: Boolean,
    val person: PersonDto,
    val credential: CredentialDto,
    val address: AddressDto,
    val profileImage: String?
)
