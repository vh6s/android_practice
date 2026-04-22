package com.example.priprava.navigation

interface INavigationRouter {
    fun navigateToAddEditEntity(id: Long?)
    fun returnBack()
    fun navigateToEntityDetail(id: Long?)
}