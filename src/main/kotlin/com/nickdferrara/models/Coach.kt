package com.nickdferrara.models

import org.bson.codecs.pojo.annotations.BsonId
import org.litote.kmongo.Id

data class Coach(
    @BsonId val id: Id<Coach>? = null,
    val isActive: Boolean,
    val person: Person,
    val credential: Credential,
    val address: Address,
    val profileImage: String?
)
