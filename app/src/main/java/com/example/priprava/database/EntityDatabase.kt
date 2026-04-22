package com.example.priprava.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Entity::class], version = 1,  exportSchema = true)
abstract class EntityDatabase : RoomDatabase() {
    abstract fun entitiesDao(): EntityDao

    companion object {

        private var instance: EntityDatabase? = null

        fun getDatabase(context: Context): EntityDatabase {
            if (instance == null) {
                synchronized(EntityDatabase::class.java) {
                    if (instance == null) {
                        instance = Room.databaseBuilder(
                            context.applicationContext,
                            EntityDatabase::class.java,
                            "database"
                        ).build()
                    }


                }
            }

            return instance!!

        }


    }
}