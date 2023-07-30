package com.zeroillusion.demonetworkapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.zeroillusion.demonetworkapp.ui.error.components.ErrorScreen
import com.zeroillusion.demonetworkapp.ui.main.components.MainScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.MainScreen.route,
        modifier = modifier
    ) {
        composable(Screen.MainScreen.route) { MainScreen(navController) }
        composable(Screen.ErrorScreen.route) { ErrorScreen(navController) }
    }
}