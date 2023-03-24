package com.nickdferrara.models

import kotlinx.serialization.Serializable

@Serializable
data class Person(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val fullName: String = "$firstName $lastName"
)
