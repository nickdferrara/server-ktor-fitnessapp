package com.nickdferrara.extension

import com.nickdferrara.dto.LocationDto
import com.nickdferrara.models.Location

fun Location.toDto(): LocationDto =
    LocationDto(
        address = this.address.toDto(),
        description = this.description
    )

fun LocationDto.toLocation(): Location =
    Location(
        address = this.address.toAddress(),
        description = this.description
    )