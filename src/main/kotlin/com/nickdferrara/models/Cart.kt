package com.nickdferrara.models

data class Cart(
    var _id: String? = null,
    val userId: String,
    val productList: List<Product>
)
