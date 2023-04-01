package com.nickdferrara.extension

import com.nickdferrara.dto.UserDto
import com.nickdferrara.models.User

fun User.toDto(): UserDto =
    UserDto(
        id = this.id.toString(),
        person = this.person?.toDto(),
        credential = this.credential.toDto(),
        address = this.address?.toDto(),
        membership = this.membership?.toDto(),
        profileImage = this.profileImage
    )

fun UserDto.toUser(): User =
    User(
        person = this.person?.toPerson(),
        credential = this.credential.toCredential(),
        address = this.address?.toAddress(),
        membership = this.membership?.toMembership(),
        profileImage = this.profileImage
    )