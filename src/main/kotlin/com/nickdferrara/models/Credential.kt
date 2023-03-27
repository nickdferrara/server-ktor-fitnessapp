package com.nickdferrara.models

import org.bson.codecs.pojo.annotations.BsonId
import org.litote.kmongo.Id

data class Credential(
    @BsonId val id: Id<Credential>? = null,
    val email: String,
    val password: String,
)
