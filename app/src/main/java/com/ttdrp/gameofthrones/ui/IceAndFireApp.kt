package com.ttdrp.gameofthrones.ui

import androidx.compose.animation.Crossfade
import androidx.compose.runtime.Composable
import com.ttdrp.gameofthrones.data.AppContainer
import com.ttdrp.gameofthrones.data.houses.IHousesRepository
import com.ttdrp.gameofthrones.ui.houses.HouseScreen
import com.ttdrp.gameofthrones.ui.houses.HousesScreen
import com.ttdrp.gameofthrones.ui.theme.GameOfThronesTheme

@Composable
fun IceAndFireApp(
    appContainer: AppContainer,
    navigationViewModel: NavigationViewModel
) {
    GameOfThronesTheme {
        AppContent(
            navigationViewModel = navigationViewModel,
            housesRepository = appContainer.housesRepository
        )
    }
}

@Composable
private fun AppContent(
    navigationViewModel: NavigationViewModel,
    housesRepository: IHousesRepository
) {
    Crossfade(navigationViewModel.currentScreen) { screen ->
        when (screen) {
            is Screen.Overview -> HousesScreen(
                navigateTo = navigationViewModel::navigateTo,
                housesRepository = housesRepository
            )
            is Screen.House -> HouseScreen(
                houseName = screen.name,
                housesRepository = housesRepository
            )
        }
    }
}