package com.nickdferrara.service

import com.nickdferrara.models.User
import org.bson.types.ObjectId
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.coroutine.replaceOne
import org.litote.kmongo.eq
import java.util.*


class UserService(
    database: CoroutineDatabase
) {
    private val userCollection = database.getCollection<User>()

    suspend fun create(user: User): User  {
        user._id = UUID.randomUUID().toString()
        userCollection.insertOne(user)
        return user
    }

    suspend fun findAll(): List<User> =
        userCollection.find()
            .toList()

    suspend fun findById(id: String): User? {
        return userCollection
            .findOne(User::_id eq id)
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