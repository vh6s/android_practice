package com.example.adroid_homework2.navigation

import androidx.navigation.NavController

class NavigationRouterImpl(private val navController: NavController): INavigationRouter {
    override fun navigateToAddEditTraining(id: Long?) {
        navController.navigate(ScreenDestination.AddEditTraining(id = id))
    }

    override fun returnBack() {
        navController.popBackStack()
    }

    override fun navigateToTrainingDetail(id: Long?) {
        navController.navigate(ScreenDestination.TrainingDetail(id = id))
    }

    override fun navigateToTrainingStatistics() {
        navController.navigate(ScreenDestination.TrainingStatistics)
    }
}