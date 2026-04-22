package com.example.priprava.database

import kotlinx.coroutines.flow.Flow

interface IEntityRepository {
    suspend fun insert(entity: Entity): Long
    fun getAll(): Flow<List<Entity>>
    suspend fun update(entity: Entity)
    fun getByid(id: Long?): Flow<Entity?>
}