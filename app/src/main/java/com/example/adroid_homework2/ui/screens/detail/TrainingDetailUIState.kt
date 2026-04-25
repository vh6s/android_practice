package com.example.adroid_homework2.ui.screens.detail

import com.example.adroid_homework2.database.TrainingData

data class TrainingDetailUIState(
    val training: TrainingData? = null,
    val isLoading: Boolean = true,
    val showDeleteDialog: Boolean = false
)
