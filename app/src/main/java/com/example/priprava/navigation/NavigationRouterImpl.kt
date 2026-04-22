package com.example.priprava.navigation

import android.util.Log
import androidx.navigation.NavController
import com.example.priprava.database.IEntityRepository

class NavigationRouterImpl(private val navController: NavController) : INavigationRouter {
    override fun navigateToAddEditEntity(id: Long?) {
        navController.navigate(ScreenDestination.AddEditEntity(id = id))
    }

    override fun returnBack() {
        navController.popBackStack()
    }

    override fun navigateToEntityDetail(id: Long?) {
        navController.navigate(ScreenDestination.EntityDetail(id = id))
    }
}