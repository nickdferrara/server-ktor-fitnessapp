package com.nickdferrara.service

import com.nickdferrara.models.Coach
import com.nickdferrara.models.Credential
import org.bson.types.ObjectId
import org.litote.kmongo.Id
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.coroutine.replaceOne
import org.litote.kmongo.eq
import org.litote.kmongo.id.toId
import java.util.*

class CoachService(
    database: CoroutineDatabase
) {
    private val coachCollection = database.getCollection<Coach>()

    suspend fun create(coach: Coach): Coach {
        coach._id = UUID.randomUUID().toString()
        coachCollection.insertOne(coach)
        return coach
    }

    suspend fun findAll(): List<Coach> =
        coachCollection.find()
            .toList()

    suspend fun findById(id: String): Coach? {
        return coachCollection
            .findOne(Coach::_id eq id)
    }

    suspend fun updateById(id: String, request: Coach): Boolean =
        findById(id)
            ?.let { coach ->
                val updateResult = coachCollection.replaceOne(
                    coach.copy(
                        person = request.person,
                        credential = request.credential,
                        address = request.address,
                        isActive = request.isActive,
                        profileImage = request.profileImage
                    )
                )
                updateResult.modifiedCount == 1L
            } ?: false

    suspend fun deleteById(id: String): Boolean {
        val deleteResult = coachCollection.deleteOneById(ObjectId(id))
        return deleteResult.deletedCount == 1L
    }
}