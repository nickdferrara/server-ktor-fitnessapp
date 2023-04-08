package com.nickdferrara.models

data class Coach(
    var _id: String? = null,
    val isActive: Boolean,
    val person: Person,
    val credential: Credential,
    val address: Address,
    val profileImage: String?
)
