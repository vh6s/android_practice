package com.example.priprava.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.priprava.R
import com.example.priprava.database.IEntityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class AddEditEntityViewModel @Inject constructor(private val repository: IEntityRepository) : ViewModel(), AddEditEntityScreenActions {
    private val _addEditEntityUIState: MutableStateFlow<AddEditEntityUIState> =
        MutableStateFlow(AddEditEntityUIState())

    val addEditEntityUIState = _addEditEntityUIState.asStateFlow()

    override fun onTextChanged(text: String) {
        _addEditEntityUIState.value =
            _addEditEntityUIState.value.copy(
                entity = _addEditEntityUIState.value.entity.copy(text = text)
            )
    }

    override fun onTitleChange(title: String) {
        _addEditEntityUIState.value =
            _addEditEntityUIState.value.copy(
                entity = _addEditEntityUIState.value.entity.copy(title = title)
            )
    }

    override fun saveEntity() {
        if (_addEditEntityUIState.value.entity.text.isNotEmpty()) {
            viewModelScope.launch {
                repository.insert(_addEditEntityUIState.value.entity)
                _addEditEntityUIState.value = _addEditEntityUIState.value.copy(
                    entitySaved = true
                )
            }
        } else {
            _addEditEntityUIState.value = _addEditEntityUIState.value.copy(
                entityTextError = R.string.cannot_be_empty
            )
        }
    }
}