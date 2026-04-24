package com.example.adroid_homework2.database

import androidx.room.TypeConverter

//Tato trida pridava databazi funkcionalitu pro zpracovani enumu.
//Nauci se prevadet z enumu na string, ktery je db zpracovatelny.

class ActivityTypeConverter {

    // Prevodník textu na enumovou hodnotu
    @TypeConverter
    fun toActivityType(value: String): ActivityType {
        return enumValueOf<ActivityType>(value)
    }

    // Prevodnik z enumu na text
    @TypeConverter
    fun fromActivityType(value: ActivityType): String {
        return value.name
    }
}