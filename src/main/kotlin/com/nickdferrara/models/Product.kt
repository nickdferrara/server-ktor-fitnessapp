package com.nickdferrara.models

data class Product(
    var _id: String? = null,
    val name: String,
    val description: String,
    val quantity: Int = 0,
    val price: Long,
    val image: String?,
)
