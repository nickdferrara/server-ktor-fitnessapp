package com.nickdferrara.dto

import kotlinx.serialization.Serializable

@Serializable
data class MembershipDto(
    val id: String? = null,
    val number: Int,
    val startDate: String,
    val endDate: String,
    val membershipType: MembershipTypeDto
)
