package com.nickdferrara.dto

import kotlinx.serialization.Serializable

@Serializable
data class ProductDto(
    var _id: String? = null,
    val name: String,
    val description: String,
    val quantity: Int = 0,
    val price: Long,
    val image: String?,
)
