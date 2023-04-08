package com.nickdferrara.extension

import com.nickdferrara.dto.UserDto
import com.nickdferrara.models.User
import org.litote.kmongo.Id

fun User.toDto(): UserDto =
    UserDto(
        _id = this._id,
        person = this.person?.toDto(),
        credential = this.credential.toDto(),
        address = this.address?.toDto(),
        membership = this.membership?.toDto(),
        profileImage = this.profileImage
    )

fun UserDto.toUser(): User =
    User(
        _id = this._id,
        person = this.person?.toPerson(),
        credential = this.credential.toCredential(),
        address = this.address?.toAddress(),
        membership = this.membership?.toMembership(),
        profileImage = this.profileImage
    )