package com.nickdferrara.extension

import com.nickdferrara.dto.MembershipTypeDto
import com.nickdferrara.models.MembershipType

fun MembershipType.toDto(): MembershipTypeDto =
    MembershipTypeDto(
        description = this.description,
        isReoccuring = this.isReoccuring
    )

fun MembershipTypeDto.toMembershipType(): MembershipType =
    MembershipType(
        description = this.description,
        isReoccuring = this.isReoccuring
    )