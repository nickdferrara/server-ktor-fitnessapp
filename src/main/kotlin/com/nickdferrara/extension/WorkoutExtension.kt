package com.nickdferrara.extension

import com.nickdferrara.dto.UserDto
import com.nickdferrara.dto.WorkoutDto
import com.nickdferrara.models.User
import com.nickdferrara.models.Workout
import java.time.LocalDateTime

fun Workout.toDto(): WorkoutDto =
    WorkoutDto(
        location = this.location.toDto(),
        description = this.description,
        capacity = this.capacity,
        duration = this.duration,
        startDate = this.startDate.toString(),
        startTime = this.startTime,
        instructor = this.instructor.toDto(),
        roster = this.roster.map(User::toDto),
        waitlist = this.waitlist.map(User::toDto)
    )

fun WorkoutDto.toWorkout(): Workout =
    Workout(
        location = this.location.toLocation(),
        description = this.description,
        capacity = this.capacity,
        duration = this.duration,
        startDate = LocalDateTime.parse(this.startDate),
        startTime = this.startTime,
        instructor = this.instructor.toCoach(),
        roster = this.roster.map(UserDto::toUser),
        waitlist = this.waitlist.map(UserDto::toUser)
    )