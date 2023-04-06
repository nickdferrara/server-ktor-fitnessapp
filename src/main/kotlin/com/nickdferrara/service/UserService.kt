package com.nickdferrara.service

import com.nickdferrara.models.User
import com.nickdferrara.models.Workout
import org.bson.types.ObjectId
import org.litote.kmongo.Id
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.coroutine.replaceOne
import org.litote.kmongo.eq
import org.litote.kmongo.id.toId

class UserService(
    database: CoroutineDatabase
) {
    private val userCollection = database.getCollection<User>()

    suspend fun create(user: User): Id<User>?  {
        userCollection.insertOne(user)
        return user.id
    }

    suspend fun findAll(): List<User> =
        userCollection.find()
            .toList()

    suspend fun findById(id: String): User? {
        val bsonId: Id<User> = ObjectId(id).toId()
        return userCollection
            .findOne(User::id eq bsonId)
    }

    suspend fun updateById(id: String, request: User): Boolean =
        findById(id)
            ?.let { user ->
                val updateResult = userCollection.replaceOne(
                    user.copy(
                        person = request.person,
                        credential = request.credential,
                        address = request.address,
                        membership = request.membership,
                        profileImage = request.profileImage
                    )
                )
                updateResult.modifiedCount == 1L
            } ?: false

    suspend fun deleteById(id: String): Boolean {
        val deleteResult = userCollection.deleteOneById(ObjectId(id))
        return deleteResult.deletedCount == 1L
    }
}