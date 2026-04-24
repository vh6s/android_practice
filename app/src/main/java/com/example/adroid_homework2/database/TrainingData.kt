package com.example.adroid_homework2.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trainings")
data class TrainingData(
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null,
    var title: String,
    var place: String,
    var trainingLength: Int,
    var burnedCalories: Double,
    var activityType: ActivityType,
    var note: String? = null
)
