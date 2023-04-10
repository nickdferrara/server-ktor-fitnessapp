package com.nickdferrara.models

data class User(
    var _id: String? = null,
    val person: Person?,
    val credential: Credential,
    val address: Address?,
    val membership: Membership?,
    val profileImage: String?,
    val role: String
)
