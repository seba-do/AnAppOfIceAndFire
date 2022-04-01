package com.ttdrp.gameofthrones.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.ttdrp.gameofthrones.ui.housedetails.HouseScreen
import com.ttdrp.gameofthrones.ui.houses.HousesScreen
import com.ttdrp.gameofthrones.ui.theme.GameOfThronesTheme
import com.ttdrp.gameofthrones.viewmodels.HouseViewModel
import com.ttdrp.gameofthrones.viewmodels.HousesViewModel

@Composable
fun IceAndFireApp() {
    val navController = rememberNavController()

    GameOfThronesTheme {
        NavHost(navController = navController, startDestination = "houses") {
            composable("houses") {
                val housesViewModel = hiltViewModel<HousesViewModel>()
                HousesScreen(navController, housesViewModel)
            }
            composable(
                route = "house/{houseName}",
                arguments = listOf(navArgument("houseName") { type = NavType.StringType })
            ) { backStackEntry ->
                val houseViewModel = hiltViewModel<HouseViewModel>()
                HouseScreen(
                    navController = navController,
                    houseViewModel = houseViewModel,
                    houseName = backStackEntry.arguments?.getString("houseName") ?: ""
                )
            }
        }
    }
}