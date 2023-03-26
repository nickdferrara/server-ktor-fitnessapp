package com.nickdferrara.models

import org.bson.codecs.pojo.annotations.BsonId
import org.litote.kmongo.Id


data class Person(
    @BsonId val id: Id<Person>? = null,
    val firstName: String,
    val lastName: String,
)
