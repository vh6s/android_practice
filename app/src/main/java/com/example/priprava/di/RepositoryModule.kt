package com.example.priprava.di

import com.example.priprava.database.EntityDao
import com.example.priprava.database.EntityRepoImpl
import com.example.priprava.database.IEntityRepository
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
    fun provideEntityRepository(dao: EntityDao): IEntityRepository {
        return EntityRepoImpl(dao)
    }

}