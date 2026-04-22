package com.example.priprava.ui.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.priprava.database.IEntityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class EntityListViewModel @Inject constructor(private val repository: IEntityRepository) : ViewModel(){

    private val _entityListUIState: MutableStateFlow<EntityListUIState> =
        MutableStateFlow(EntityListUIState())

    val entityListUIState = _entityListUIState.asStateFlow()

    init {
        loadEntities()
    }

    private fun loadEntities(){
        viewModelScope.launch {
            repository.getAll().collect { entity ->
                Log.v("EntityListViewModel:loadEntities", entity.toString())
                _entityListUIState.value = _entityListUIState.value.copy(entities = entity)
            }
        }
    }

}