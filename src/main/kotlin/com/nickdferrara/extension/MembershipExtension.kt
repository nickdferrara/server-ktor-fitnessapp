package com.nickdferrara.extension

import com.nickdferrara.dto.MembershipDto
import com.nickdferrara.models.Membership

fun Membership.toDto(): MembershipDto =
    MembershipDto(
        id = this.id.toString(),
        number = this.number,
        startDate = this.startDate,
        endDate = this.endDate,
        membershipType = this.membershipType.toDto(),
    )

fun MembershipDto.toMembership(): Membership =
    Membership(
        number = this.number,
        startDate = this.startDate,
        endDate = this.endDate,
        membershipType = this.membershipType.toMembershipType()
    )