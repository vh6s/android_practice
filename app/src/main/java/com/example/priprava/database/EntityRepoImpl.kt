package com.example.priprava.database

import kotlinx.coroutines.flow.Flow

class EntityRepoImpl(private val dao: EntityDao) : IEntityRepository {
    override suspend fun insert(entity: Entity): Long {
        return dao.insert(entity)
    }

    override fun getAll(): Flow<List<Entity>> = dao.getAll()

    override suspend fun update(entity: Entity) {
        dao.update(entity)
    }

    override fun getByid(id: Long?): Flow<Entity> = dao.getById(id)

}