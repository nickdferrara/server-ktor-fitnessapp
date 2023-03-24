package com.nickdferrara.models

import kotlinx.serialization.Serializable

@Serializable
data class Membership(
    val id: Int,
    val number: Int,
    val startDate: String,
    val endDate: String,
    val membershipType: MembershipType
)
