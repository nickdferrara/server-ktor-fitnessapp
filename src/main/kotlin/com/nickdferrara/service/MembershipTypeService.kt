package com.nickdferrara.service

import com.nickdferrara.models.MembershipType
import org.bson.types.ObjectId
import org.litote.kmongo.Id
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.coroutine.replaceOne
import org.litote.kmongo.eq
import org.litote.kmongo.id.toId

class MembershipTypeService(
    database: CoroutineDatabase
) {
    private val membershipTypeCollection = database.getCollection<MembershipType>()

    suspend fun create(membershipType: MembershipType): Id<MembershipType>? {
        membershipTypeCollection.insertOne(membershipType)
        return membershipType.id
    }

    suspend fun findAll(): List<MembershipType> =
        membershipTypeCollection.find()
            .toList()

    suspend fun findById(id: String): MembershipType? {
        val bsonId: Id<MembershipType> = ObjectId(id).toId()
        return membershipTypeCollection
            .findOne(MembershipType::id eq bsonId)
    }

    suspend fun updateById(id: String, request: MembershipType): Boolean =
        findById(id)
            ?.let { membershipType ->
                val updateResult = membershipTypeCollection.replaceOne(
                    membershipType.copy(
                        description = request.description,
                        isReoccuring = request.isReoccuring
                    )
                )
                updateResult.modifiedCount == 1L
            } ?: false

    suspend fun deleteById(id: String): Boolean {
        val deleteResult = membershipTypeCollection.deleteOneById(ObjectId(id))
        return deleteResult.deletedCount == 1L
    }
}

