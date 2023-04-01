package com.nickdferrara.dto

import com.nickdferrara.models.Address
import com.nickdferrara.models.Credential
import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    val id: String? = null,
    val person: PersonDto?,
    val credential: CredentialDto,
    val address: AddressDto?,
    val membership: MembershipDto?,
    val profileImage: String?

)
