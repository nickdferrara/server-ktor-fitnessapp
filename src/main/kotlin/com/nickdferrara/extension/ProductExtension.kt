package com.nickdferrara.extension

import com.nickdferrara.dto.ProductDto
import com.nickdferrara.models.Product

fun Product.toDto(): ProductDto =
    ProductDto(
        _id = this._id,
        name = this.name,
        description = this.description,
        quantity = this.quantity,
        price = this.price,
        image = this.image
    )

fun ProductDto.toProduct(): Product =
    Product(
        _id = this._id,
        name = this.name,
        description = this.description,
        quantity = this.quantity,
        price = this.price,
        image = this.image
    )