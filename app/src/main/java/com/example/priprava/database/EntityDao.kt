package com.example.priprava.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface EntityDao {

    @Insert
    suspend fun insert(entity: Entity): Long

    @Query("SELECT * FROM entities")
    fun getAll(): Flow<List<Entity>>

    @Update
    suspend fun update(entity: Entity)

    @Query("SELECT * FROM entities WHERE id = :id")
    fun getById(id: Long?): Flow<Entity>

}