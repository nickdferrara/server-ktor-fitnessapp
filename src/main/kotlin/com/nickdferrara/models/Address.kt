package com.nickdferrara.models

import org.bson.codecs.pojo.annotations.BsonId
import org.litote.kmongo.Id

data class Address(
    val street: String,
    val city: String,
    val state: String,
    val zip: Int
)
