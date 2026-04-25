package com.example.adroid_homework2.database

import com.example.adroid_homework2.ui.screens.statistics.TrainingStats
import kotlinx.coroutines.flow.Flow

interface ITrainingRepo {
    suspend fun insert(training: TrainingData): Long

    fun getAll(): Flow<List<TrainingData>>

    suspend fun update(training: TrainingData)

    fun getById(id: Long?): Flow<TrainingData?>

    suspend fun delete(training: TrainingData)

    fun getTrainingStatistics(): Flow<TrainingStats?>
}