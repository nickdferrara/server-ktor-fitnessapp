package com.nickdferrara.extension

import com.nickdferrara.dto.CredentialDto
import com.nickdferrara.models.Credential

fun Credential.toDto(): CredentialDto =
    CredentialDto(
        email = this.email,
        password = this.password
    )

fun CredentialDto.toCredential(): Credential =
    Credential(
        email = this.email,
        password = this.password
    )