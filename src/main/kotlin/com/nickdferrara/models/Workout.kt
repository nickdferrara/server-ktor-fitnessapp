package com.nickdferrara.models

import org.bson.codecs.pojo.annotations.BsonId
import org.litote.kmongo.Id
import java.time.LocalDateTime

data class Workout(
    @BsonId val id: Id<Workout>? = null,
    val location: Location,
    val description: String,
    val capacity: Int,
    val startTime: String,
    val duration: Int,
    val startDate: LocalDateTime,
    val instructor: Coach,
    val roster: List<User>,
    val waitlist: List<User>,
)
