package com.example.priprava.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.priprava.ui.screens.AddEditEntityScreen
import com.example.priprava.ui.screens.EntityDetailScreen
import com.example.priprava.ui.screens.EntityListScreen

@Composable
fun EntityNavGraph(
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

        composable<ScreenDestination.EntityList> {
            EntityListScreen(navigationRouter = navRouter)
        }

        composable<ScreenDestination.EntityDetail> { backStackEntry ->
            val route = backStackEntry.toRoute<ScreenDestination.EntityDetail>()
            EntityDetailScreen(navigationRouter = navRouter, id = route.id)
        }

        composable<ScreenDestination.AddEditEntity> { backStackEntry ->
            val destination: ScreenDestination.AddEditEntity = backStackEntry.toRoute()
            AddEditEntityScreen(
                navigationRouter = navRouter,
                id = destination.id
            )
        }
    }
}
