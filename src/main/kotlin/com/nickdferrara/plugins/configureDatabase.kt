package com.nickdferrara.plugins

import io.ktor.server.application.*
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

fun Application.configureDatabase() {
    val mongoPw = System.getenv("MONGO_PW")
    val dbName = "db-ktor-fitnessapp"
    val db = KMongo.createClient(
        connectionString = "mongodb+srv://nickdferrara:$mongoPw@cluster0.w7u9u7d.mongodb.net/$dbName?retryWrites=true&w=majority"
    ).coroutine
        .getDatabase(dbName)
    }