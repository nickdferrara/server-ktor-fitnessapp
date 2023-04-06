package com.nickdferrara.models

import org.bson.codecs.pojo.annotations.BsonId
import org.litote.kmongo.Id

data class Location(
    @BsonId val id: Id<Coach>? = null,
    val address: Address,
    val description: String,
)
