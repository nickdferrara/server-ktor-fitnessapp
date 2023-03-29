package com.nickdferrara.service

import com.nickdferrara.models.Credential
import org.bson.types.ObjectId
import org.litote.kmongo.Id
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.coroutine.replaceOne
import org.litote.kmongo.eq
import org.litote.kmongo.id.toId


class CredentialService(
    database: CoroutineDatabase
) {
    private val credentialCollection = database.getCollection<Credential>()

    suspend fun create(credential: Credential): Id<Credential>?  {
        credentialCollection.insertOne(credential)
        return credential.id
    }

    suspend fun findAll(): List<Credential> =
        credentialCollection.find()
            .toList()

    suspend fun findById(id: String): Credential? {
        val bsonId: Id<Credential> = ObjectId(id).toId()
        return credentialCollection
            .findOne(Credential::id eq bsonId)
    }

    suspend fun updateById(id: String, request: Credential): Boolean =
        findById(id)
            ?.let { credential ->
                val updateResult = credentialCollection.replaceOne(
                    credential.copy(
                        email = request.email,
                        password = request.password
                    )
                )
                updateResult.modifiedCount == 1L
            } ?: false

    suspend fun deleteById(id: String): Boolean {
        val deleteResult = credentialCollection.deleteOneById(ObjectId(id))
        return deleteResult.deletedCount == 1L
    }
}