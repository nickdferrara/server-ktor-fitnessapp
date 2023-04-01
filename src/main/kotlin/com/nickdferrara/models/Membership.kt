package com.nickdferrara.models

import org.bson.codecs.pojo.annotations.BsonId
import org.litote.kmongo.Id

data class Membership(
    val number: Int,
    val startDate: String,
    val endDate: String,
    val membershipType: MembershipType
)
