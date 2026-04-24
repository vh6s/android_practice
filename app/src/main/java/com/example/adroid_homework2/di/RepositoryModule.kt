package com.example.adroid_homework2.di

import com.example.adroid_homework2.database.ITrainingRepo
import com.example.adroid_homework2.database.TrainingDao
import com.example.adroid_homework2.database.TrainingRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideTrainingRepository(dao: TrainingDao): ITrainingRepo {
        return TrainingRepoImpl(dao)
    }
}