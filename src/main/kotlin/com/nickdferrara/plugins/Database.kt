package com.nickdferrara.plugins

import io.ktor.server.application.*
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

fun Application.configureDatabase(): CoroutineDatabase {
    val mongoUsername = System.getenv("MONGO_USERNAME")
    val mongoPassword = System.getenv("MONGO_PASSWORD")
    val mongoDbName = System.getenv("MONGO_DBNAME")
    val client = KMongo.createClient(
        connectionString = "mongodb+srv://$mongoUsername:$mongoPassword@cluster0.kg571te.mongodb.net/?retryWrites=true&w=majority"
    ).coroutine

    return client.getDatabase("$mongoDbName")
}