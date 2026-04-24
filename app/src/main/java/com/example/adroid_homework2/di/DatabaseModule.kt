package com.example.adroid_homework2.di

import android.content.Context
import com.example.adroid_homework2.database.TrainingDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): TrainingDatabase {
        return TrainingDatabase.getDatabase(context)
    }
}