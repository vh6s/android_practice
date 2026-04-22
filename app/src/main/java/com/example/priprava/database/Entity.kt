package com.example.priprava.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "entities")
data class Entity(
    var title: String,
    var text: String,
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null
)
