package com.nickdferrara.dto

import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    var _id: String?,
    val person: PersonDto?,
    val credential: CredentialDto,
    val address: AddressDto?,
    val membership: MembershipDto?,
    val profileImage: String?
)
