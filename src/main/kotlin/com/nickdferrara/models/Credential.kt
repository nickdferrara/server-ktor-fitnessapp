package com.nickdferrara.models

import org.bson.codecs.pojo.annotations.BsonId
import org.litote.kmongo.Id

data class Credential(
    val email: String,
    val password: String,
)
