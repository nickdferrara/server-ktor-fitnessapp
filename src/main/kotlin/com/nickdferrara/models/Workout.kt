package com.nickdferrara.models

data class Workout(
    val id: Int,
    val location: Location,
    val description: String,
    val capacity: Int,
    val startTime: String,
    val duration: Int,
    val date: String,
    val instructor: Coach
)
