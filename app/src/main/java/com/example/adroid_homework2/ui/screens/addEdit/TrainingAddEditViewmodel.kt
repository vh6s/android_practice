package com.example.adroid_homework2.ui.screens.addEdit

import androidx.lifecycle.ViewModel
import com.example.adroid_homework2.database.ActivityType
import com.example.adroid_homework2.database.ITrainingRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class TrainingAddEditViewmodel @Inject constructor(private val repo: ITrainingRepo) : ViewModel(), TrainingAddEditScreenActions {
    private val _trainingAddEditUIState: MutableStateFlow<TrainingAddEditUIState> =
        MutableStateFlow(TrainingAddEditUIState())

    val trainingAddEditUIState = _trainingAddEditUIState.asStateFlow()
    private val validateTitle = ValidateTitle()

    override fun onTitleChange(title: String) {
        val result = validateTitle.execute(title)

        _trainingAddEditUIState.value =
            _trainingAddEditUIState.value.copy(
                title = _trainingAddEditUIState.value.title.copy(
                    value = title,
                    error = result.errorMessage
                )
            )
    }

    override fun onPlaceChange(place: String) {
        TODO("Not yet implemented")
    }

    override fun onTrainingLengthChange(length: Int) {
        TODO("Not yet implemented")
        // nesmi byt negativni a 0, zadne pismena jen cisla
    }

    override fun onBurnedCaloriesChange(burnedCalories: Double) {
        TODO("Not yet implemented")
        //nesmi byt negativni a 0, zadne pismena jen cisla
    }

    override fun onActivityTypeChange(activity: ActivityType) {
        TODO("Not yet implemented")
    }

    override fun onNoteChange(note: String) {
        TODO("Not yet implemented")
        // max 6 radku, mozna jde resit nejakou funkci pres compose
    }

    override fun saveTraining() {
        TODO("Not yet implemented")
    }
}