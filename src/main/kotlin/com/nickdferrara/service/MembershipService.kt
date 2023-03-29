package com.nickdferrara.service

import com.nickdferrara.models.Membership
import com.nickdferrara.models.Person
import org.bson.types.ObjectId
import org.litote.kmongo.Id
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.coroutine.replaceOne
import org.litote.kmongo.eq
import org.litote.kmongo.id.toId

class MembershipService(
    database: CoroutineDatabase
) {
    private val membershipCollection = database.getCollection<Membership>()

    suspend fun create(membership: Membership): Id<Membership>?  {
        membershipCollection.insertOne(membership)
        return membership.id
    }

    suspend fun findAll(): List<Membership> =
        membershipCollection.find()
            .toList()

    suspend fun findById(id: String): Membership? {
        val bsonId: Id<Membership> = ObjectId(id).toId()
        return membershipCollection
            .findOne(Membership::id eq bsonId)
    }

    suspend fun updateById(id: String, request: Membership): Boolean =
        findById(id)
            ?.let { membership ->
                val updateResult = membershipCollection.replaceOne(
                    membership.copy(
                        number = request.number,
                        startDate = request.startDate,
                        endDate = request.endDate,
                        membershipType = request.membershipType
                    )
                )
                updateResult.modifiedCount == 1L
            } ?: false

    suspend fun deleteById(id: String): Boolean {
        val deleteResult = membershipCollection.deleteOneById(ObjectId(id))
        return deleteResult.deletedCount == 1L
    }
}