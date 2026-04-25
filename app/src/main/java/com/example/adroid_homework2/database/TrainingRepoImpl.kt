package com.example.adroid_homework2.database

import com.example.adroid_homework2.ui.screens.statistics.TrainingStats
import kotlinx.coroutines.flow.Flow

class TrainingRepoImpl(private val dao: TrainingDao): ITrainingRepo {

    override suspend fun insert(training: TrainingData): Long = dao.insert(training)

    override fun getAll(): Flow<List<TrainingData>> = dao.getAll()

    override suspend fun update(training: TrainingData) = dao.update(training)

    override fun getById(id: Long?): Flow<TrainingData?> = dao.getById(id)

    override suspend fun delete(training: TrainingData) = dao.delete(training)

    override fun getTrainingStatistics(): Flow<TrainingStats?> = dao.getTrainingStatistics()
}