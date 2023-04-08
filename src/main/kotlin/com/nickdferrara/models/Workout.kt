package com.nickdferrara.models

import java.time.LocalDateTime

data class Workout(
    var _id: String? = null,
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
