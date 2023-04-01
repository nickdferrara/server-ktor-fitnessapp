package com.nickdferrara.models

import org.bson.codecs.pojo.annotations.BsonId
import org.litote.kmongo.Id


data class Person(
    val firstName: String,
    val lastName: String,
)
