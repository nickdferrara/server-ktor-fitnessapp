package com.nickdferrara.service

import com.nickdferrara.models.Person
import org.bson.types.ObjectId
import org.litote.kmongo.Id
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo
import org.litote.kmongo.reactivestreams.getCollection

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
}