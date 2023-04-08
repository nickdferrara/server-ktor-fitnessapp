package com.nickdferrara.extension

import com.nickdferrara.dto.CoachDto
import com.nickdferrara.models.Coach

fun Coach.toDto(): CoachDto =
    CoachDto(
        _id = this._id.toString(),
        person = this.person.toDto(),
        credential = this.credential.toDto(),
        address = this.address.toDto(),
        isActive = this.isActive,
        profileImage = this.profileImage
    )

fun CoachDto.toCoach(): Coach =
    Coach(
        _id = this._id,
        person = this.person.toPerson(),
        credential = this.credential.toCredential(),
        address = this.address.toAddress(),
        isActive = this.isActive,
        profileImage = this.profileImage
    )