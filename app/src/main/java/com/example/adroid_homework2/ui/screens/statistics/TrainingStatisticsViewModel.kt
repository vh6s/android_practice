package com.example.adroid_homework2.ui.screens.statistics

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adroid_homework2.database.ITrainingRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class TrainingStatisticsViewModel @Inject constructor(private val repository: ITrainingRepo) : ViewModel() {

    private val _trainingStatisticsUIState: MutableStateFlow<TrainingStatisticsUIState> =
        MutableStateFlow(TrainingStatisticsUIState(isLoading = true))

    val trainingStatisticsUIState = _trainingStatisticsUIState.asStateFlow()

    init {
        loadStatistics()
    }

    private fun loadStatistics() {
        viewModelScope.launch {

            repository.getTrainingStatistics().collect { stats ->
                if (stats == null || stats.totalTrainings == 0) {
                    _trainingStatisticsUIState.value = TrainingStatisticsUIState(isLoading = false)
                } else {
                    _trainingStatisticsUIState.value = TrainingStatisticsUIState(
                        isLoading = false,
                        totalTrainings = stats.totalTrainings ?: 0,
                        totalMinutes = stats.totalMinutes ?: 0,
                        mostCommonActivity = stats.mostCommonActivity
                    )
                }
            }
        }
    }
}