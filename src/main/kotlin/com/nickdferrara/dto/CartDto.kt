package com.nickdferrara.dto

import kotlinx.serialization.Serializable

@Serializable
data class CartDto(
    var _id: String? = null,
    val userId: String,
    val productList: List<ProductDto>
)
