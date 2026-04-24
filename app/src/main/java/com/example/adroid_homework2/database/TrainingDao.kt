package com.example.adroid_homework2.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TrainingDao {
    @Insert
    suspend fun insert(training: TrainingData): Long

    @Query("SELECT * FROM trainings")
    fun getAll(): Flow<List<TrainingData>>

    @Update
    suspend fun update(training: TrainingData)

    @Query("SELECT * FROM trainings WHERE id = :id")
    fun getById(id: Long?): Flow<TrainingData?>

    @Delete
    suspend fun delete(training: TrainingData)
}