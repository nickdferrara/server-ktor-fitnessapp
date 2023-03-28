package com.nickdferrara.models

import org.bson.codecs.pojo.annotations.BsonId
import org.litote.kmongo.Id

data class Membership(
    @BsonId val id: Id<Membership>? = null,
    val number: Int,
    val startDate: String,
    val endDate: String,
    val membershipType: MembershipType
)
