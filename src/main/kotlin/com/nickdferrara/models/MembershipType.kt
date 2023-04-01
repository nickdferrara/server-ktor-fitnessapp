package com.nickdferrara.models

import org.bson.codecs.pojo.annotations.BsonId
import org.litote.kmongo.Id

data class MembershipType(
    val description: Description,
    val isReoccuring: Boolean,
)

enum class Description {
    WEEKLY,
    MONTHLY,
    YEARLY,
}
