package com.nickdferrara.service

import com.nickdferrara.models.Coach
import com.nickdferrara.models.Location
import org.bson.types.ObjectId
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.coroutine.replaceOne
import org.litote.kmongo.eq
import java.util.*

class LocationService(
    database: CoroutineDatabase
) {
    private val locationCollection = database.getCollection<Location>()

    suspend fun create(location: Location): Location {
        location._id = UUID.randomUUID().toString()
        locationCollection.insertOne(location)
        return location
    }

    suspend fun findAll(): List<Location> =
        locationCollection.find()
            .toList()

    suspend fun findById(id: String): Location? {
        return locationCollection
            .findOne(Location::_id eq id)
    }

    suspend fun updateById(id: String, request: Location): Boolean =
        findById(id)
            ?.let { location ->
                val updateResult = locationCollection.replaceOne(
                    location.copy(
                        address = request.address,
                        description = request.description                    )
                )
                updateResult.modifiedCount == 1L
            } ?: false

    suspend fun deleteById(id: String): Boolean {
        val deleteResult = locationCollection.deleteOneById(ObjectId(id))
        return deleteResult.deletedCount == 1L
    }
}