package com.nickdferrara.service

import com.nickdferrara.models.User
import org.litote.kmongo.Id
import org.litote.kmongo.coroutine.CoroutineDatabase

class UserService(
    database: CoroutineDatabase
) {
    private val userCollection = database.getCollection<User>()

    suspend fun create(user: User): Id<User>?  {
        userCollection.insertOne(user)
        return user.id
    }
}