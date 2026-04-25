package com.example.adroid_homework2.ui.screens.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adroid_homework2.database.ITrainingRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class TrainingListViewModel @Inject constructor(private val repository: ITrainingRepo) : ViewModel() {
    private val _trainingListUIState: MutableStateFlow<TrainingListUIState> =
        MutableStateFlow(TrainingListUIState())

    val trainingListUIState = _trainingListUIState.asStateFlow()

    init {
        loadTrainings()
    }

    private fun loadTrainings() {
        viewModelScope.launch {
            repository.getAll().collect { training ->
                _trainingListUIState.value = _trainingListUIState.value.copy(trainings = training)
            }
        }
    }
}