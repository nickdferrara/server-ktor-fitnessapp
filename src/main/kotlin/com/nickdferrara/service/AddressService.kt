package com.nickdferrara.service

import com.nickdferrara.models.Address
import org.litote.kmongo.Id
import org.litote.kmongo.coroutine.CoroutineDatabase

class AddressService(
    database: CoroutineDatabase
)
{
    private val addressCollection = database.getCollection<Address>()

    suspend fun create(address: Address): Id<Address>?  {
        addressCollection.insertOne(address)
        return address.id
    }
}