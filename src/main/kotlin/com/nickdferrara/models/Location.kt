package com.nickdferrara.models

data class Location(
    var _id: String? = null,
    val description: String,
    val address: Address,
)
