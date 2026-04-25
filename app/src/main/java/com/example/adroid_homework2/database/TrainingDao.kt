package com.example.adroid_homework2.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.adroid_homework2.ui.screens.statistics.TrainingStats
import kotlinx.coroutines.flow.Flow

@Dao
interface TrainingDao {
    @Insert
    suspend fun insert(training: TrainingData): Long

    @Query("SELECT * FROM trainings ORDER BY id DESC")
    fun getAll(): Flow<List<TrainingData>>

    @Update
    suspend fun update(training: TrainingData)

    @Query("SELECT * FROM trainings WHERE id = :id")
    fun getById(id: Long?): Flow<TrainingData?>

    @Delete
    suspend fun delete(training: TrainingData)

    @Query("""
        SELECT
            COUNT(*) as totalTrainings,
            SUM(trainingLength) as totalMinutes,
            (SELECT activityType FROM trainings GROUP BY activityType ORDER BY COUNT(*) DESC LIMIT 1) as mostCommonActivity
        FROM trainings
    """)
    fun getTrainingStatistics(): Flow<TrainingStats?>
}