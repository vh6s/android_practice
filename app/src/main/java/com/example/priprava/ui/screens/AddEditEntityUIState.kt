package com.example.priprava.ui.screens

import com.example.priprava.database.Entity

data class AddEditEntityUIState(
    var entity: Entity = Entity("", ""),
    var entitySaved: Boolean = false,
    var entityTextError: Int? = null
)
