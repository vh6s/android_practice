package com.example.adroid_homework2.ui.screens.addEdit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adroid_homework2.database.ActivityType
import com.example.adroid_homework2.database.ITrainingRepo
import com.example.adroid_homework2.database.TrainingData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrainingAddEditViewmodel @Inject constructor(private val repo: ITrainingRepo) : ViewModel(), TrainingAddEditScreenActions {
    private val _trainingAddEditUIState: MutableStateFlow<TrainingAddEditUIState> =
        MutableStateFlow(TrainingAddEditUIState())

    val trainingAddEditUIState = _trainingAddEditUIState.asStateFlow()
    private val validateText = ValidateText()
    private val validateNumber = ValidateNumber()
    private val validateDouble = ValidateDouble()
    private val validateNote = ValidateNote()

    override fun onTitleChange(title: String) {
        val result = validateText.execute(title)

        _trainingAddEditUIState.value =
            _trainingAddEditUIState.value.copy(
                title = _trainingAddEditUIState.value.title.copy(
                    value = title,
                    error = result.errorMessage
                )
            )
    }

    override fun onPlaceChange(place: String) {
        val result = validateText.execute(place)

        _trainingAddEditUIState.value =
            _trainingAddEditUIState.value.copy(
                place = _trainingAddEditUIState.value.place.copy(
                    value = place,
                    error = result.errorMessage
                )
            )
    }

    override fun onTrainingLengthChange(length: String) {
        val result = validateNumber.execute(length)

        _trainingAddEditUIState.value =
            _trainingAddEditUIState.value.copy(
                trainingLength = _trainingAddEditUIState.value.trainingLength.copy(
                    value = length,
                    error = result.errorMessage
                )
            )
    }

    override fun onBurnedCaloriesChange(burnedCalories: String) {
        val result = validateDouble.execute(burnedCalories)

        _trainingAddEditUIState.value =
            _trainingAddEditUIState.value.copy(
                burnedCalories = _trainingAddEditUIState.value.burnedCalories.copy(
                    value = burnedCalories,
                    error = result.errorMessage
                )
            )
    }

    override fun onActivityTypeChange(activity: ActivityType) {
        _trainingAddEditUIState.value =
            _trainingAddEditUIState.value.copy(
                activityType = _trainingAddEditUIState.value.activityType.copy(
                    value = activity,
                    error = null
                )
            )
    }

    override fun onNoteChange(note: String) {
        val result = validateNote.execute(note)

        _trainingAddEditUIState.value =
            _trainingAddEditUIState.value.copy(
                note = _trainingAddEditUIState.value.note.copy(
                    value = note,
                    error = result.errorMessage
                )
            )
    }

    fun getTrainingById(id: Long?) {
        if (id == null) return

        viewModelScope.launch {
            repo.getById(id).collectLatest { training ->
                // pokud je pruchod null, tak ho preskoci, at to neukonci celou funkci
                training ?: return@collectLatest

                _trainingAddEditUIState.value = TrainingAddEditUIState(
                    title = FormField(training.title),
                    place = FormField(training.place),
                    trainingLength = FormField(training.trainingLength.toString()),
                    burnedCalories = FormField(training.burnedCalories.toString()),
                    activityType = FormField(training.activityType),
                    note = FormField(training.note ?: "")
                )
            }
        }
    }

    override fun saveTraining() {
        _trainingAddEditUIState.update { it.copy(showErrors = true)}
        val state = _trainingAddEditUIState.value

        // zvalidujeme vsechny policka presd ulozenim
        val titleResult = validateText.execute(state.title.value)
        val placeResult = validateText.execute(state.place.value)
        val lengthResult = validateNumber.execute(state.trainingLength.value)
        val caloriesResult = validateDouble.execute(state.burnedCalories.value)
        val noteResult = validateNote.execute(state.note.value)

        // seznam pripadnych erroru
        val hasError = listOf(
            titleResult,
            placeResult,
            lengthResult,
            caloriesResult,
            noteResult
        ).any { !it.isSuccessful }

        // kdyz je chyba v seznamu, updatne se ui
        if (hasError) {
            _trainingAddEditUIState.value = state.copy(
                title = state.title.copy(error = titleResult.errorMessage),
                place = state.place.copy(error = placeResult.errorMessage),
                trainingLength = state.trainingLength.copy(error = lengthResult.errorMessage),
                burnedCalories = state.burnedCalories.copy(error = caloriesResult.errorMessage),
                note = state.note.copy(error = noteResult.errorMessage)
            )
            return
        }

        // prevod ze stringu zpatky na ciselne hodnoty
        val trainingLength = state.trainingLength.value.toInt()
        val burnedCalories = state.burnedCalories.value
            .replace(",", ".")
            .toDouble()

        // vytvoreni entity pro ulozeni/update
        val training = TrainingData(
            title = state.title.value,
            place = state.place.value,
            trainingLength = trainingLength,
            burnedCalories = burnedCalories,
            activityType = state.activityType.value,
            note = state.note.value.ifBlank { null }
        )

        // ulozeniii
        viewModelScope.launch {
            repo.insert(training)
            _trainingAddEditUIState.value = state.copy(trainingSaved = true)
        }
    }
}