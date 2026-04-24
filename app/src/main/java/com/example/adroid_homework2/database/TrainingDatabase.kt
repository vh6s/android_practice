package com.example.adroid_homework2.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [TrainingData::class], version = 1, exportSchema = true)
@TypeConverters(ActivityTypeConverter::class)
abstract class TrainingDatabase: RoomDatabase() {
    abstract fun trainingsDao(): TrainingDao

    companion object {

        private var instance: TrainingDatabase? = null

        fun getDatabase(context: Context): TrainingDatabase {
            if (instance == null) {
                synchronized(TrainingDatabase::class.java) {
                    if (instance == null) {
                        instance = Room.databaseBuilder(
                            context.applicationContext,
                            TrainingDatabase::class.java,
                            "database"
                        ).build()
                    }


                }
            }

            return instance!!

        }


    }

}