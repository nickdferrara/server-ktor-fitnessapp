package com.nickdferrara.service

import com.nickdferrara.models.User
import org.litote.kmongo.coroutine.CoroutineDatabase

class UserService(
    database: CoroutineDatabase
) {
    private val userollection = database.getCollection<User>()
}