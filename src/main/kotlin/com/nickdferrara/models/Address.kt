package com.nickdferrara.models

import com.nickdferrara.service.AddressService
import org.bson.codecs.pojo.annotations.BsonId
import org.litote.kmongo.Id

data class Address(
    @BsonId val id: Id<Address>? = null,
    val street: String,
    val city: String,
    val state: String,
    val zip: Int
)
