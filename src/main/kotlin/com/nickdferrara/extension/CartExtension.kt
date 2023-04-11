package com.nickdferrara.extension

import com.nickdferrara.dto.CartDto
import com.nickdferrara.dto.ProductDto
import com.nickdferrara.models.Cart
import com.nickdferrara.models.Product

fun Cart.toDto(): CartDto =
    CartDto(
        _id = this._id,
        userId = this.userId,
        productList = this.productList.map(Product::toDto)
    )

fun CartDto.toCart(): Cart =
    Cart(
        _id = this._id,
        userId = this.userId,
        productList = this.productList.map(ProductDto::toProduct)
    )