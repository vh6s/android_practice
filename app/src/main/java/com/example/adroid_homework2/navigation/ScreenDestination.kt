package com.example.adroid_homework2.navigation

import kotlinx.serialization.Serializable

sealed interface ScreenDestination {

    @Serializable
    data object TrainingList : ScreenDestination

    @Serializable
    data class AddEditTraining(val id: Long? = null) : ScreenDestination

    @Serializable
    data class TrainingDetail(val id: Long?) : ScreenDestination

    @Serializable
    data object TrainingStatistics : ScreenDestination
}