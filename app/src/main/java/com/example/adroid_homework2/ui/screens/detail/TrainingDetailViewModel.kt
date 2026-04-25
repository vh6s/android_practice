package com.example.adroid_homework2.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adroid_homework2.database.ITrainingRepo
import com.example.adroid_homework2.database.TrainingData
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class TrainingDetailViewModel @Inject constructor(private val repository: ITrainingRepo) : ViewModel() {

    private val _trainingDetailUIState: MutableStateFlow<TrainingDetailUIState> = MutableStateFlow(
        TrainingDetailUIState())
    val trainingDetailUIState = _trainingDetailUIState.asStateFlow()

    fun getTrainingById(id: Long?) {
        viewModelScope.launch {
            repository.getById(id).collect { training ->
                _trainingDetailUIState.value = TrainingDetailUIState(training = training, isLoading = false)
            }
        }
    }

    fun onDeleteClick() {
        _trainingDetailUIState.value =
            _trainingDetailUIState.value.copy(showDeleteDialog = true)
    }

    fun onDeleteConfirm() {
        val training = _trainingDetailUIState.value.training ?: return

        viewModelScope.launch {
            repository.delete(training)

            _trainingDetailUIState.value =
                _trainingDetailUIState.value.copy(showDeleteDialog = false)
        }
    }

    fun onDeleteDismiss() {
        _trainingDetailUIState.value =
            _trainingDetailUIState.value.copy(showDeleteDialog = false)
    }
}