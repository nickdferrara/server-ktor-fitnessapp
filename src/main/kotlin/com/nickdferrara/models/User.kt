package com.nickdferrara.models

import org.bson.codecs.pojo.annotations.BsonId
import org.litote.kmongo.Id

data class User(
    var _id: String? = null,
    val person: Person?,
    val credential: Credential,
    val address: Address?,
    val membership: Membership?,
    val profileImage: String?
)
