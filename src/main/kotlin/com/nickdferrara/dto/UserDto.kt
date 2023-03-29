package com.nickdferrara.dto

import com.nickdferrara.models.Address
import com.nickdferrara.models.Credential
import com.nickdferrara.models.Membership
import com.nickdferrara.models.Person

data class UserDto(
    val id: String? = null,
    val person: Person?,
    val credential: Credential,
    val address: Address?,
    val membership: Membership?,
    val profileImage: String?
)
