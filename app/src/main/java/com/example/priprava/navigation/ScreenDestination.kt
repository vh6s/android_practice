package com.example.priprava.navigation

import kotlinx.serialization.Serializable

sealed interface ScreenDestination {

    @Serializable
    data object EntityList : ScreenDestination

    @Serializable
    data class AddEditEntity(val id: Long? = null) : ScreenDestination

    @Serializable
    data class EntityDetail(val id: Long?) : ScreenDestination

}