package com.example.adroid_homework2.di

import com.example.adroid_homework2.database.TrainingDao
import com.example.adroid_homework2.database.TrainingDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {

    @Provides
    @Singleton
    fun provideTrainingDao(trainingDatabase: TrainingDatabase): TrainingDao {
        return trainingDatabase.trainingsDao()
    }
}