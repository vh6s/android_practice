package com.example.adroid_homework2.ui.screens.statistics

import com.example.adroid_homework2.database.ActivityType

data class TrainingStatisticsUIState(
    val isLoading: Boolean = true,
    val totalTrainings: Int = 0,
    val totalMinutes: Int = 0,
    val mostCommonActivity: ActivityType? = null
)
