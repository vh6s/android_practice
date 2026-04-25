package com.example.adroid_homework2.ui.screens.statistics

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adroid_homework2.database.ITrainingRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
class TrainingStatisticsViewModel @Inject constructor(private val repository: ITrainingRepo) : ViewModel() {

    // todo
    val uiState: StateFlow<TrainingStatisticsUIState> = repository.getTrainingStatistics()
        .map { tuple ->
            if (tuple == null || tuple.totalTrainings == 0) {
                // Prázdný stav, pokud nejsou žádná data
                TrainingStatisticsUIState(isLoading = false)
            } else {
                TrainingStatisticsUIState(
                    isLoading = false,
                    totalTrainings = tuple.totalTrainings ?: 0,
                    totalMinutes = tuple.totalMinutes ?: 0,
                    mostCommonActivity = tuple.mostCommonActivity
                )
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = TrainingStatisticsUIState(isLoading = true)
        )
}