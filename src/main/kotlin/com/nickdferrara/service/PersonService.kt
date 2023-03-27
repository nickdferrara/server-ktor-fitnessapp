package com.nickdferrara.service

import com.nickdferrara.models.Person
import org.bson.types.ObjectId
import org.litote.kmongo.Id
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.coroutine.replaceOne
import org.litote.kmongo.eq
import org.litote.kmongo.id.toId
import org.litote.kmongo.or
import org.litote.kmongo.regex

class PersonService(
    database: CoroutineDatabase
) {
    private val personCollection = database.getCollection<Person>()

     suspend fun create(person: Person): Id<Person>?  {
         personCollection.insertOne(person)
         return person.id
    }

    suspend fun findAll(): List<Person> =
        personCollection.find()
            .toList()

    suspend fun findById(id: String): Person? {
        val bsonId: Id<Person> = ObjectId(id).toId()
        return personCollection
            .findOne(Person::id eq bsonId)
    }

    suspend fun findByName(name: String): List<Person> {
        val caseSensitiveTypeSafeFilter = or(Person::firstName regex name, Person::lastName regex name)
        return personCollection.find(caseSensitiveTypeSafeFilter)
            .toList()
    }

    suspend fun updateById(id: String, request: Person): Boolean =
        findById(id)
            ?.let { person ->
                val updateResult = personCollection.replaceOne(person.copy(firstName = request.firstName, lastName = request.lastName))
                updateResult.modifiedCount == 1L
            } ?: false


    suspend fun deleteById(id: String): Boolean {
        val deleteResult = personCollection.deleteOneById(ObjectId(id))
        return deleteResult.deletedCount == 1L
    }
}
