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
class EntityDetailViewModel @Inject constructor(private val repository: IEntityRepository) : ViewModel() {

    private val _entityDetailUIState: MutableStateFlow<EntityDetailUIState> = MutableStateFlow(EntityDetailUIState())
    val entityDetailUIState = _entityDetailUIState.asStateFlow()

    fun getEntityById(id: Long?) {
        viewModelScope.launch {
            repository.getByid(id).collect { entity ->
                Log.v("EntityDetailViewModel:getEntityById", entity.toString())
                _entityDetailUIState.value = EntityDetailUIState(entity = entity, isLoading = false)
            }
        }
    }

}