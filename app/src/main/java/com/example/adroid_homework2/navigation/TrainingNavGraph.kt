package com.example.adroid_homework2.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.adroid_homework2.ui.screens.addEdit.TrainingAddEditScreen
import com.example.adroid_homework2.ui.screens.detail.TrainingDetailScreen
import com.example.adroid_homework2.ui.screens.list.TrainingListScreen
import com.example.adroid_homework2.ui.screens.statistics.TrainingStatisticsScreen

@Composable
fun TrainingNavGraph(
    startDestination: ScreenDestination,
    navHostController: NavHostController = rememberNavController(),
    navRouter: INavigationRouter = remember {
        NavigationRouterImpl(navHostController)
    }
) {
    NavHost(
        navController = navHostController,
        startDestination = startDestination
    ) {
        composable<ScreenDestination.TrainingList> {
            TrainingListScreen(navigationRouter = navRouter)
        }

        composable<ScreenDestination.TrainingDetail> { backStackEntry ->
            val route = backStackEntry.toRoute<ScreenDestination.TrainingDetail>()
            TrainingDetailScreen(
                navigationRouter = navRouter,
                id = route.id)
        }

        composable<ScreenDestination.AddEditTraining> { backStackEntry ->
            val destination: ScreenDestination.AddEditTraining = backStackEntry.toRoute()
            TrainingAddEditScreen(
                navigationRouter = navRouter,
                id = destination.id
            )
        }

        composable<ScreenDestination.TrainingStatistics> {
            TrainingStatisticsScreen(navigationRouter = navRouter)
        }
    }
}