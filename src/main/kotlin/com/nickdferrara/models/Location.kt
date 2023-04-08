package com.nickdferrara.models

data class Location(
    var _id: String? = null,
    val address: Address,
    val description: String,
)
