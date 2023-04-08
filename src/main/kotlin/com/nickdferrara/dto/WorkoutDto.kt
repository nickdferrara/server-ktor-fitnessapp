package com.nickdferrara.dto

import kotlinx.serialization.Serializable

@Serializable
data class WorkoutDto(
    var _id: String? = null,
    val location: LocationDto,
    val description: String,
    val capacity: Int,
    val startTime: String,
    val duration: Int,
    val startDate: String,
    val instructor: CoachDto,
    val roster: List<UserDto>,
    val waitlist: List<UserDto>,
)
