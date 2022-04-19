package com.ttdrp.gameofthrones.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.ttdrp.gameofthrones.ui.housedetails.HouseScreen
import com.ttdrp.gameofthrones.ui.houses.HousesScreen
import com.ttdrp.gameofthrones.ui.lorddetails.LordScreen
import com.ttdrp.gameofthrones.ui.theme.GameOfThronesTheme
import com.ttdrp.gameofthrones.viewmodels.HouseViewModel
import com.ttdrp.gameofthrones.viewmodels.HousesViewModel
import com.ttdrp.gameofthrones.viewmodels.LordViewModel

val LocalNavController = compositionLocalOf<NavController> { error("No NavController found") }
val LocalTopAppBarController =
    compositionLocalOf<TopAppBarController> { error("No TopAppBarController found") }

@Composable
fun IceAndFireApp() = GameOfThronesTheme {
    val navController = rememberNavController()
    val topAppBarController = remember { TopAppBarController() }

    CompositionLocalProvider(
        LocalNavController provides navController,
        LocalTopAppBarController provides topAppBarController
    ) {
        Scaffold(
            topBar = {
                val currentTopBar by topAppBarController.current.collectAsState()
                currentTopBar()
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = "houses",
                modifier = Modifier.padding(innerPadding)
            ) {
                composable("houses") {
                    val housesViewModel = hiltViewModel<HousesViewModel>()
                    HousesScreen(housesViewModel)
                }
                composable(
                    route = "house/{houseName}",
                    arguments = listOf(navArgument("houseName") { type = NavType.StringType })
                ) { backStackEntry ->
                    val houseViewModel = hiltViewModel<HouseViewModel>()
                    HouseScreen(
                        houseViewModel = houseViewModel,
                        houseId = backStackEntry.arguments?.getString("houseName") ?: ""
                    )
                }
                composable(
                    route = "lord/{lordName}",
                    arguments = listOf(navArgument("lordName") { type = NavType.StringType })
                ) { backStackEntry ->
                    val lordViewModel = hiltViewModel<LordViewModel>()
                    LordScreen(
                        lordViewModel = lordViewModel,
                        lordId = backStackEntry.arguments?.getString("lordName") ?: ""
                    )
                }
            }
        }
    }
}
