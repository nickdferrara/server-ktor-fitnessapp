package com.nickdferrara.models

data class Membership(
    val id: Int,
    val number: Int,
    val startDate: String,
    val endDate: String,
    val membershipType: MembershipType
)
