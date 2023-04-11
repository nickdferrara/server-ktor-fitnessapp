package com.nickdferrara.models

data class Membership(
    val number: Int,
    val startDate: String,
    val endDate: String,
    val membershipType: MembershipType
)
