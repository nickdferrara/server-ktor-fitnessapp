package com.nickdferrara.extension

import com.nickdferrara.dto.UserDto
import com.nickdferrara.models.User

fun User.toDto(): UserDto =
    UserDto(
        id = this.id.toString(),
        person = this.person,
        credential = this.credential,
        address = this.address,
        membership = this.membership,
        profileImage = this.profileImage
    )

fun UserDto.toUser(): User =
    User(
        person = this.person,
        credential = this.credential,
        address = this.address,
        membership = this.membership,
        profileImage = this.profileImage
    )