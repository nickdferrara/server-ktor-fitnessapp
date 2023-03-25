package com.nickdferrara.models

data class Coach(
    val id: Int,
    val person: Person,
    val credential: Credential,
    val address: Address
)
