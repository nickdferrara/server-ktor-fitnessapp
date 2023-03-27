package com.nickdferrara.extension

import com.nickdferrara.dto.PersonDto
import com.nickdferrara.models.Person

fun Person.toDto(): PersonDto =
    PersonDto(
        id = this.id.toString(),
        firstName = this.firstName,
        lastName = this.lastName
    )

fun Person.fullName(): String =
    "${this.firstName} ${this.lastName}"

fun PersonDto.toPerson(): Person =
    Person(
        firstName = this.firstName,
        lastName = this.lastName
    )

