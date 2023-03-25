package com.nickdferrara.models

data class User(
    val id: Int,
    val person: Person?,
    val credential: Credential,
    val address: Address?,
    val membership: Membership?,
    val profileImage: String?
)
