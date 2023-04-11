package com.nickdferrara.extension

import com.nickdferrara.dto.LocationDto
import com.nickdferrara.models.Location

fun Location.toDto(): LocationDto =
    LocationDto(
        _id = this._id,
        description = this.description,
        address = this.address.toDto(),
    )

fun LocationDto.toLocation(): Location =
    Location(
        _id = this._id,
        description = this.description,
        address = this.address.toAddress()
    )