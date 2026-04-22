package com.example.priprava.ui.screens

import com.example.priprava.database.Entity


data class EntityDetailUIState(
    val entity: Entity? = null,
    val isLoading: Boolean = true
)
