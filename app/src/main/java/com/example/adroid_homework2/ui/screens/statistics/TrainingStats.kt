package com.example.adroid_homework2.ui.screens.statistics

import com.example.adroid_homework2.database.ActivityType

data class TrainingStats(
    val totalTrainings: Int?,
    // SUM v SQLite vrací NULL, pokud je tabulka prázdná, proto musí být Int?
    val totalMinutes: Int?,
    val mostCommonActivity: ActivityType?
)
