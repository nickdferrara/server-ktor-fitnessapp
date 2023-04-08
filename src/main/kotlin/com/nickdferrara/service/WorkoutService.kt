package com.nickdferrara.service


import com.mongodb.client.model.Filters.eq
import com.nickdferrara.models.Workout
import org.bson.types.ObjectId
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.coroutine.replaceOne
import org.litote.kmongo.eq
import java.util.*

class WorkoutService(
    database: CoroutineDatabase
) {
    private val workoutCollection = database.getCollection<Workout>()

    suspend fun create(workout: Workout): Workout  {
        workout._id = UUID.randomUUID().toString()
        workoutCollection.insertOne(workout)
        return workout
    }

    suspend fun findAll(): List<Workout> =
        workoutCollection.find()
            .toList()


    suspend fun findById(id: String): Workout? {
        return workoutCollection
            .findOne(Workout::_id eq id)
    }

    suspend fun findByUserId(id: String): Workout? {
        return workoutCollection
            .find(eq("roster._id", id)).first()
    }

    suspend fun updateById(id: String, request: Workout): Boolean =
        findById(id)
            ?.let { workout ->
                val updateResult = workoutCollection.replaceOne(
                    workout.copy(
                        location = request.location,
                        description = request.description,
                        capacity = request.capacity,
                        startTime = request.startTime,
                        duration = request.duration,
                        startDate = request.startDate,
                        instructor = request.instructor,
                        roster = request.roster,
                        waitlist = request.waitlist
                    )
                )
                updateResult.modifiedCount == 1L
            } ?: false

    suspend fun deleteById(id: String): Boolean {
        val deleteResult = workoutCollection.deleteOneById(ObjectId(id))
        return deleteResult.deletedCount == 1L
    }
}