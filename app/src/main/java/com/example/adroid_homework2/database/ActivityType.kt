package com.example.adroid_homework2.database

enum class ActivityType {
    RUN, CYCLE, SWIM, GYM
}

fun ActivityType.toCzechName(): String {
    return when(this) {
        ActivityType.RUN -> "Běh"
        ActivityType.CYCLE -> "Kolo"
        ActivityType.SWIM -> "Plavání"
        ActivityType.GYM -> "Posilovna"
    }
}